package com.project.real.model;

import com.project.real.model.enums.ObjectType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "LOGIN")
@PrimaryKeyJoinColumn(name = "LOGIN_ID")
public class Login extends ParentObject {

    private static final long serialVersionUID = 1L;

    @Column(name = "LOGIN_NAME", nullable = false, unique = true)
    private String loginName;

    @OneToOne
    @JoinColumn(name = "PERSON_ID", nullable = false, unique = true)
    private Person person;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FINGERPRINTDATA")
    private String fingerPrintData;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive = false;

    @Column(name = "IS_LOCKED", nullable = false)
    private boolean isLocked = false;

    @Column(name = "IS_SUPERUSER", nullable = false)
    private Boolean isSuperUser = false;

    @Column(name = "IS_ADMIN", nullable = false)
    private Boolean isAdmin = false;

    @Column(name = "EXTERNAL", nullable = false)
    private boolean external = false;

    @Transient
    private Map<String, Boolean> permissions = new HashMap<String, Boolean>();

    @Transient
    private String username;

    //to track password change
    @Transient
    private Boolean flag;

    @Transient
    private Integer activeUsers;

    @Transient
    private Integer inActiveUsers;

    @Transient
    private Integer activeExternalUsers;

    @Transient
    private Integer inActiveExternalUsers;

    @Transient
    private Integer totalUsers;

    @Transient
    private Boolean existPerson = Boolean.FALSE;

    public Login() {
        super("LOGIN");
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

}
