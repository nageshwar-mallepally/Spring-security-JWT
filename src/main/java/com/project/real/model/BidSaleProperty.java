package com.project.real.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "BID_SALE_PROPERTY")
@PrimaryKeyJoinColumn(name = "ID")
public class BidSaleProperty extends ParentObject{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "number_of_units", length = 10000)
    private Integer requiredUnits;

    @Column(name = "price_quotation")
    private Double priceQuotation;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PROPERTY_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Property property;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PROPERTY_SALE_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private PropertyForSale propertySale;

    @Column(name = "STATUS")
    private String status = "NEW";

    public BidSaleProperty() {
        super("BIDSALEPROPERTY");
    }

}
