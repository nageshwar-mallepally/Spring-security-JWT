package com.project.real.controller;

import com.project.real.model.BidSaleProperty;
import com.project.real.model.Property;
import com.project.real.model.PropertyForSale;
import com.project.real.repo.PropertyRepository;
import com.project.real.service.BidSalePropertyService;
import com.project.real.service.PropertyForSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/property/sale")
public class PropertyForSaleController {

    @Autowired
    private PropertyForSaleService propertyForSaleService;

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
    public PropertyForSale create(@RequestBody PropertyForSale propertyForSale) {
        return propertyForSaleService.create(propertyForSale);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<PropertyForSale> getAll() {
        return propertyForSaleService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        propertyForSaleService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PropertyForSale getProperty(@PathVariable("id") Integer id) {
        return propertyForSaleService.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Integer id, @RequestBody PropertyForSale propertyForSale) {
        propertyForSaleService.update(id, propertyForSale);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<PropertyForSale> getPropertiesByPage(Pageable pageable) {
        return propertyForSaleService.getSaleProperties(pageable);
    }

}
