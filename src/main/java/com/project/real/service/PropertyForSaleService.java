package com.project.real.service;

import com.project.real.model.BidSaleProperty;
import com.project.real.model.Property;
import com.project.real.model.PropertyForSale;
import com.project.real.repo.PropertyForSaleRepository;
import com.project.real.repo.PropertyRepository;
import com.project.real.repo.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class PropertyForSaleService {

    @Autowired
    private PropertyForSaleRepository propertyForSaleRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserRepository userRepository;

    public List<PropertyForSale> findBySalePropertiesByProperty(Property property) {
        return propertyForSaleRepository.findByProperty(property);
    }

    private void setValues(PropertyForSale propertyForSale){
        Property property = propertyRepository.getOne(propertyForSale.getProperty());
        propertyForSale.setOwnerName(userRepository.findById(property.getOwner().longValue()).get().getFirstName());
        propertyForSale.setPropertyName(property.getName());
        propertyForSale.setPropertyId(property.getPropertyId());
        propertyForSale.setCategory(property.getCategory());
        propertyForSale.setUnits(property.getUnits());
        propertyForSale.setPropertyType(property.getPropertyType());
    }

    public List<PropertyForSale> getAll() {
        List<PropertyForSale> propertyForSales = propertyForSaleRepository.findAll();
        for (PropertyForSale propertyForSale : propertyForSales) {
            setValues(propertyForSale);
        }
        return propertyForSales;
    }

    public Page<PropertyForSale> getSaleProperties(Pageable pageable) {
        Page<PropertyForSale> propertyForSales = propertyForSaleRepository.findAll(pageable);
        for (PropertyForSale propertyForSale : propertyForSales) {
            setValues(propertyForSale);
        }
        return propertyForSales;
    }

    public void deletePropertyForSale(Integer id) {
        propertyForSaleRepository.deleteById(id);
    }

    public PropertyForSale create(PropertyForSale propertyForSale) {
        return propertyForSaleRepository.save(propertyForSale);
    }

    public PropertyForSale update(Integer id, PropertyForSale propertyForSale) {
        propertyForSale.setId(id);
        return propertyForSaleRepository.save(propertyForSale);
    }

    public PropertyForSale get(Integer id) {
        return propertyForSaleRepository.getOne(id);
    }

    @Transactional
    public void delete(Integer id) {
        propertyForSaleRepository.deleteById(id);
    }
}
