package com.project.real.service;

import com.project.real.model.BidSaleProperty;
import com.project.real.model.Property;
import com.project.real.model.PropertyForSale;
import com.project.real.repo.BidSalePropertyRepository;
import com.project.real.repo.PropertyForSaleRepository;
import com.project.real.repo.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BidSalePropertyService {

    @Autowired
    private BidSalePropertyRepository bidSalePropertyRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyForSaleRepository propertyForSaleRepository;

    public List<BidSaleProperty> findByByProperty(Property property) {
        return bidSalePropertyRepository.findByProperty(property);
    }

    public BidSaleProperty get(Integer id) {
        return bidSalePropertyRepository.getOne(id);
    }

    public List<BidSaleProperty> getAll() {
        return bidSalePropertyRepository.findAll();
    }

    public Page<BidSaleProperty> getBidSaleProperties(Pageable pageable) {
        return bidSalePropertyRepository.findAll(pageable);
    }

    public List<BidSaleProperty> getBidSalePropertiesBySale(Integer id, Pageable pageable) {
        PropertyForSale propertyForSale = propertyForSaleRepository.findById(id).get();
        return bidSalePropertyRepository.findByPropertySale(propertyForSale);
    }

    public void deletePropertyForSale(Integer id) {
        bidSalePropertyRepository.deleteById(id);
    }

    public BidSaleProperty create(BidSaleProperty propertyForSale) {
        BidSaleProperty bidSaleProperty = bidSalePropertyRepository.save(propertyForSale);
        if (bidSaleProperty.getProperty().getPropertyType().toString().equalsIgnoreCase("NEW")) {
            bidSaleProperty.setStatus("APPROVE");
            editPropertiesBasedOnBid(bidSaleProperty);
        }
        return bidSaleProperty;
    }

    public BidSaleProperty update(Integer id, BidSaleProperty propertyForSale) {
        propertyForSale.setId(id);
        return bidSalePropertyRepository.save(propertyForSale);
    }

    public BidSaleProperty approveBid(Integer id, BidSaleProperty bid) {
        bid.setId(id);
        BidSaleProperty bidproperty1 = bidSalePropertyRepository.findById(id).get();
        if (bidproperty1.getStatus().toString().toString().equals("NEW") && bid.getStatus().toString().equals("APPROVE")) {
            editPropertiesBasedOnBid(bid);
        }
        return bidSalePropertyRepository.save(bid);
    }

    public void delete(Integer id) {
        bidSalePropertyRepository.deleteById(id);
    }

    private void editPropertiesBasedOnBid(BidSaleProperty bid) {
        Property property = bid.getProperty();
        property.setUnits(property.getUnits() - bid.getRequiredUnits());
        PropertyForSale propertyForSale = bid.getPropertySale();
        Double funded = (double) (bid.getRequiredUnits() * 100 / propertyForSale.getSaleUnits());
        System.out.print(funded);
        propertyForSale.setFunded(propertyForSale.getFunded() + funded);
        propertyForSale.setSaleUnits(propertyForSale.getSaleUnits() - bid.getRequiredUnits());
        propertyRepository.save(property);
        propertyForSaleRepository.save(propertyForSale);
        Property property1 = null;
        property1 = propertyRepository.findByPropertyIdAndOwner(property.getPropertyId(), bid.getCreatedBy());
        if (property1 == null) property1 = new Property();
        property1.setPropertyType("Resale");
        property1.setPropertyId(property.getPropertyId());
        property1.setName(property.getName());
        property1.setUnits(property1.getUnits() + bid.getRequiredUnits());
        property1.setCategory(property.getCategory());
        property1.setDirection(property.getDirection());
        property1.setAddress(property.getAddress());
        property1.setDistrict(property.getDistrict());
        property1.setPincode(property.getPincode());
        property1.setCountry(property.getCountry());
        property1.setCity(property.getCity());
        property1.setState(property.getState());
        property1.setStatus("APPROVE");
        if (property1.getParent() == null) property1.setParent(property.getId());
        property1.setOwner(bid.getCreatedBy());
        property1.setCreatedBy(bid.getCreatedBy());
        property1.setModifiedBy(bid.getCreatedBy());
        property1.setObjectType("PROPERTY");
        propertyRepository.save(property1);
    }

}
