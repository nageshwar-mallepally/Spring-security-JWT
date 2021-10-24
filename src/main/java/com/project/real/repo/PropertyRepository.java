package com.project.real.repo;

import com.project.real.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    Page<Property> findByStatus(String status, Pageable pageable);

    Page<Property> findByOwner(Integer id, Pageable pageable);

    Property findByPropertyId(String propertyId);
    Property findByPropertyIdAndOwner(String propertyId, Integer owner);
}
