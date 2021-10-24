package com.project.real.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_roles")
public class UserRoles implements Serializable {

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "row_id",nullable = false)
//    private Integer rowId;


    @Id
    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name = "role_id",nullable = false )
    private Integer roleId;
}
