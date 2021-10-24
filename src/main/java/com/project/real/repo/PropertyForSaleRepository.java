package com.project.real.repo;

import com.project.real.model.Property;
import com.project.real.model.PropertyForSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PropertyForSaleRepository extends JpaRepository<PropertyForSale, Integer>{

    List<PropertyForSale> findByProperty(Property property);

}
