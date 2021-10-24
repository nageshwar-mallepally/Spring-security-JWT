package com.project.real.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.poifs.property.Parent;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "PROPERTY_FOR_SALE")
@PrimaryKeyJoinColumn(name = "ID")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PropertyForSale extends ParentObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "SALE_UNITS", length = 10000)
    private Integer saleUnits;

    @Column(name = "QUANTITY_PRICE")
    private Double qtyPrice;

    @Column(name = "FUNDED")
    private Double funded = 0.0;

    @Column(name = "PROPERTY_ID", nullable = false)
    private Integer property;

    @Transient
    private String propertyName;
    @Transient
    private String propertyId;
    @Transient
    private String category;
    @Transient
    private Integer units;
    @Transient
    private String propertyType;
    @Transient
    private String ownerName;

    public PropertyForSale() {
        super("PROPERTYFORSALE");
    }

}
