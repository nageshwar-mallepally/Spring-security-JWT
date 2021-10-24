package com.project.real.repo;

import com.project.real.model.BidSaleProperty;
import com.project.real.model.Property;
import com.project.real.model.PropertyForSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BidSalePropertyRepository extends JpaRepository<BidSaleProperty, Integer> {

    List<BidSaleProperty> findByProperty(Property property);

    List<BidSaleProperty> findByPropertySale(PropertyForSale propertyForSale);

}
