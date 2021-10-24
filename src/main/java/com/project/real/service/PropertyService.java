package com.project.real.service;

import com.project.real.model.Property;
import com.project.real.model.PropertyForSale;
import com.project.real.model.PropertyImage;
import com.project.real.repo.PropertyForSaleRepository;
import com.project.real.repo.PropertyImageRepository;
import com.project.real.repo.PropertyRepository;
import com.project.real.repo.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.Deflater;


@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyImageRepository propertyImageRepository;
    @Autowired
    private PropertyForSaleRepository propertyForSaleRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Property create(Property property) {
        Property property1 = propertyRepository.save(property);
        System.out.print(property1);
        return property1;
    }

    @Transactional
    public Property update(Property property) {
        property.setOwner(property.getCreatedBy());
        return propertyRepository.save(property);
    }

    @Transactional
    public void delete(Integer registrationId) {
        Property property = propertyRepository.getOne(registrationId);
        property.setIsDeleted(true);
        propertyRepository.deleteById(registrationId);
    }

    private void setValues(Property property) {
        final List<PropertyImage> propertyImages = propertyImageRepository.findByProperty(property);
        if (property.getParent() != null)
            property.setParentUnits(propertyRepository.findById(property.getParent()).get().getUnits());
            property.setOwnerName(userRepository.findById(property.getOwner().longValue()).get().getFirstName());
            /*property.setPropertyImages(propertyImages);*/
        for (PropertyImage retrievedImage : propertyImages) {
            PropertyImage img = new PropertyImage(retrievedImage.getId(), retrievedImage.getName(), retrievedImage.getProperty(),
                    retrievedImage.getType(), retrievedImage.getImage());
            property.getPropertyImages().add(img);
        }
    }

    @Transactional
    public List<Property> getAll() {
        List<Property> properties = propertyRepository.findAll();
        for (Property property : properties) {
            setValues(property);
        }
        return properties;
    }

    @Transactional
    public Page<Property> getProperties(Pageable pageable) {
        Page<Property> properties = propertyRepository.findAll(pageable);
        for (Property property : properties) {
            setValues(property);
        }
        return properties;
    }

    @Transactional
    public Page<Property> getPropertiesByUser(Integer userId, Pageable pageable) {
        Page<Property> properties = propertyRepository.findByOwner(userId, pageable);
        for (Property property : properties) {
            setValues(property);
        }
        return properties;
    }

    @Transactional
    public Page<Property> getNewPropertiesByPage(Pageable pageable) {
        Page<Property> properties = propertyRepository.findByStatus("NEW", pageable);
        for (Property property : properties) {
            setValues(property);
        }
        return properties;
    }

    @Transactional
    public Property get(Integer registrationId) {
        Property property = propertyRepository.getOne(registrationId);
        return property;
    }

    public void uploadImage(Integer pId, MultipartFile file) {
        try {
            System.out.println("Original Image Byte Size - " + file.getBytes().length);
            Property property = propertyRepository.findById(pId).orElse(new Property());
            PropertyImage img = new PropertyImage(file.getOriginalFilename(), property, file.getContentType(),
                    file.getBytes());
            PropertyImage image = propertyImageRepository.save(img);
        } catch (Exception e) {
            new Exception("");
        }
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

}
