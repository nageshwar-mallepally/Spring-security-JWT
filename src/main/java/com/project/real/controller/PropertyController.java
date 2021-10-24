package com.project.real.controller;

import com.project.real.model.Property;
import com.project.real.model.PropertyImage;
import com.project.real.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/property")
public class PropertyController extends BaseController {

    @Autowired
    private PropertyService propertyService;

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
    public Property create(@RequestBody Property property) {
        return propertyService.create(property);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Property> getAllProperties() {
        return propertyService.getAll();
    }

    @RequestMapping(value = "/{registrationId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("registrationId") Integer registrationId) {
        propertyService.delete(registrationId);
    }

    @RequestMapping(value = "/{registrationId}", method = RequestMethod.GET)
    public Property getProperty(@PathVariable("registrationId") Integer registrationId) {
        return propertyService.get(registrationId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Property update(@PathVariable("id") Integer id, @RequestBody Property property) {
        property.setId(id);
        return propertyService.update(property);
    }

    @RequestMapping(value = "/pimage/upload/{pId}", method = RequestMethod.POST)
    public void uplaodImage(@PathVariable("pId") Integer pId, @RequestParam("imageFile") MultipartFile file) {
        propertyService.uploadImage(pId, file);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Property> getPropertiesByPage(Pageable pageable) {
        return propertyService.getProperties(pageable);
    }

    @RequestMapping(value = "/user/{uId}",method = RequestMethod.GET)
    public Page<Property> getPropertiesByUser(@PathVariable("uId") Integer uId, Pageable pageable) {
        return propertyService.getPropertiesByUser(uId, pageable);
    }


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public Page<Property> getNewPropertiesByPage(Pageable pageable) {
        return propertyService.getNewPropertiesByPage(pageable);
    }


}
