package com.project.real.repo.security;

import com.project.real.model.security.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;




@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles,Long>{

    List<UserRoles> findByRoleId(Integer roleId);
}
