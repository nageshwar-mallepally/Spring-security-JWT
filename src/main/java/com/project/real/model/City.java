package com.project.real.model;

import com.project.real.model.enums.ObjectType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "City")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "OBJECT_ID_GEN", sequenceName = "OBJECT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OBJECT_ID_GEN")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STATE_ID", nullable = false)
    private Integer stateId;

    @Column(name = "CITY_NAME", nullable = false, unique = true)
    private String cityName;

    @Column(name = "SHORT_NAME")
    private String shortName;

}
