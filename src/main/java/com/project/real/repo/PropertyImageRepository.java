package com.project.real.repo;

import com.project.real.model.Property;
import com.project.real.model.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;




public interface PropertyImageRepository extends JpaRepository<PropertyImage, Integer> {

    PropertyImage findByName(String name);

    List<PropertyImage> findByProperty(Property property);

}
