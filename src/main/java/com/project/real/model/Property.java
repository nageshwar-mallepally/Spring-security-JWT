package com.project.real.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.real.model.enums.ObjectType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "PROPERTY")
@PrimaryKeyJoinColumn(name = "ID")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@Where(clause="IS_DELETED=1")
public class Property extends ParentObject {

    public Property() {
        super("PROPERTY");
    }

    @Column(name = "PROPERTY_TYPE")
    private String propertyType;

    @Column(name = "PROPERTY_ID")
    private String propertyId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UNITS")
    private Integer units = 0;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "DIRECTION")
    private String direction;

    @Column(name = "ADDRESS_TEXT")
    private String address;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "PINCODE")
    private Long pincode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @Column(name = "STATUS")
    private String status = "NEW";

    @Column(name = "OWNER")
    private Integer owner;

    @Column(name = "PARENT_ID")
    private Integer parent;

    @Transient
    private List<PropertyImage> propertyImages = new ArrayList();

    @Transient
    private String parentStatus;

    @Transient
    private Integer parentUnits;

    @Transient
    private String ownerName;

}
