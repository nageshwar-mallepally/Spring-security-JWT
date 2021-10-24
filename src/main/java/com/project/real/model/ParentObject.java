package com.project.real.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.real.config.ObjectTypeDeserializer;
import com.project.real.config.ObjectEntityListener;
import com.project.real.model.enums.ObjectType;
import com.project.real.utils.DateDeSerializer;
import com.project.real.utils.DateSerializer;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Table(name = "PARENT_OBJECT")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(ObjectEntityListener.class)
public abstract class ParentObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "OBJECT_ID_GEN", sequenceName = "OBJECT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OBJECT_ID_GEN")
    @Column(name = "OBJECT_ID")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_DATE", nullable = false)
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeSerializer.class)
    private Date createdDate = new Date();

    @Column(name = "CREATED_BY", nullable = false)
    private Integer createdBy;

    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeSerializer.class)
    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFIED_DATE", nullable = false)
    private Date modifiedDate = new Date();

    @Column(name = "MODIFIED_BY")
    private Integer modifiedBy;

//    @Type(type = "com.project.real.utils.EnumUserType", parameters = {@org.hibernate.annotations.Parameter(name = "enumClassName", value = "com.project.real.model.enums.ObjectType")})
    @Column(name = "OBJECT_TYPE", nullable = false)
//    @JsonDeserialize(using = ObjectTypeDeserializer.class)
    private String objectType;

    @Transient
    private Map<String, Object> attributesMap = new HashMap<>();

    public ParentObject() {
        this.objectType = "UNKNOWN";
    }

    protected ParentObject(String objectType) {
        this.objectType = objectType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

}



