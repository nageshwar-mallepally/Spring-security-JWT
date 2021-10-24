package com.project.real.controller;

import com.project.real.model.BidSaleProperty;
import com.project.real.model.Property;
import com.project.real.model.PropertyForSale;
import com.project.real.service.BidSalePropertyService;
import com.project.real.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/property/bid")
public class BidSalePropertyController {

    @Autowired
    private BidSalePropertyService bidSalePropertyService;

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
    public BidSaleProperty create(@RequestBody BidSaleProperty bidSaleProperty) {
        return bidSalePropertyService.create(bidSaleProperty);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<BidSaleProperty> getAll() {
        return bidSalePropertyService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        bidSalePropertyService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BidSaleProperty getProperty(@PathVariable("id") Integer id) {
        return bidSalePropertyService.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Integer id, @RequestBody BidSaleProperty bidSaleProperty) {
        bidSalePropertyService.update(id, bidSaleProperty);
    }

    @RequestMapping(value = "/approve/{id}", method = RequestMethod.PUT)
    public void approveBid(@PathVariable("id") Integer id, @RequestBody BidSaleProperty bidSaleProperty) {
        bidSalePropertyService.approveBid(id, bidSaleProperty);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<BidSaleProperty> getPropertiesByPage(Pageable pageable) {
        return bidSalePropertyService.getBidSaleProperties(pageable);
    }

    @RequestMapping(value= "/sale/{saleId}", method = RequestMethod.GET)
    public List<BidSaleProperty> getPropertiesBySale(@PathVariable("saleId") Integer saleId, Pageable pageable) {
        return bidSalePropertyService.getBidSalePropertiesBySale(saleId, pageable);
    }

}
