package com.project.real.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "PROPERTY_IMAGE")
public class PropertyImage implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="image",length = 1000000)
    private byte[] image;

    @Column(name = "name")
    private String name;

    @Column(name = "image_type")
    private String type;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PROPERTY_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Property property;


    public PropertyImage(String name, Property property, String type, byte[] picByte) {
        this.name = name;
        this.property = property;
        this.type = type;
        this.image = picByte;
    }

    public PropertyImage(Integer id, String name, Property property, String type, byte[] picByte) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.property = property;
        this.image = picByte;
    }

}

