package com.project.real.service;

import com.project.real.model.security.UserRoles;
import com.project.real.repo.security.RoleRepository;
import com.project.real.repo.security.UserRepository;
import com.project.real.repo.security.UserRolesRepository;
import com.project.real.model.security.Role;
import com.project.real.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;




@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    public Role createNewRole(Role role){
        return  roleRepository.save(role);
    }

    public List<Role> getAll(){
        List<Role> roles= roleRepository.findAll();

        for (Role role : roles){
            Set<User> users1 = new HashSet<>();
            List<UserRoles> userRoles = userRolesRepository.findByRoleId(role.getId());
            for(UserRoles userRole : userRoles) {
                Optional<User> user = userRepository.findById(userRole.getUserId());
                users1.add(user.get());
            }
            role.setUsers(users1);
        }
        return  roles;
    }

    public Role updateRole(Long id , Role role){
        return  roleRepository.save(role);
    }
    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }
}
