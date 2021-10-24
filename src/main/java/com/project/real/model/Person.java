package com.project.real.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.real.model.enums.ObjectType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "PERSON")
@JsonIgnoreProperties(ignoreUnknown = true)
@PrimaryKeyJoinColumn(name = "PERSON_ID")
public class Person extends ParentObject {

    private static final long serialVersionUID = 1L;

    @Column(name = "PERSON_TYPE", nullable = false)
    private Integer personType;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "PHONE_OFFICE")
    private String phoneOffice;

    @Column(name = "PHONE_MOBILE")
    private String phoneMobile;

    @Column(name = "EMAIL")
    private String email;

    @JsonIgnore
    @Column(name = "IMAGE")
    private byte[] image;

    private String mobileDevice;

    @Column(name = "DEFAULT_GROUP")
    private Integer defaultGroup;

    @Transient
    private String login;

    @Transient
    private boolean external = false;

    @Transient
    private boolean isActive = false;

    @Transient
    private boolean hasImage = false;

    public Person() {
        super("PERSON");
    }

    public String getFullName() {
        return firstName + " " + (lastName != null && !lastName.trim().isEmpty() ? lastName : "");
    }

}
