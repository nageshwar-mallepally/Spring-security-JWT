package com.project.real.service;

import com.project.real.model.security.ERole;
import com.project.real.model.security.Role;
import com.project.real.model.security.User;
import com.project.real.repo.security.RoleRepository;
import com.project.real.repo.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @PostConstruct
    public void initRoleAndUser() {

        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElse(null);
        if (adminRole == null) {
            adminRole = new Role();
//        adminRole.setId(1);
            adminRole.setName(ERole.ROLE_ADMIN);
            adminRole.setDescription("Manages all aspects of Project");
            roleRepository.save(adminRole);
        }

        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElse(null);
        if (userRole == null) {
            userRole = new Role();
//        userRole.setId(2);
            userRole.setName(ERole.ROLE_USER);
            userRole.setDescription("Manages User aspects");
            roleRepository.save(userRole);
        }


        User adminUser = userRepository.findByUsername("root").orElse(null);
        if (adminUser == null) {
            adminUser = new User();
            adminUser.setFirstName("Administrator");
//        adminUser.setId((long) 1);
            adminUser.setUsername("root");
            adminUser.setPassword(passwordEncoder.encode("x"));
            adminUser.setEmail("admin@gmail.com");
            Set adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            adminUser.setRoles(adminRoles);

            userRepository.save(adminUser);
        }

        Set userRoles = new HashSet<>();
        userRoles.add(userRole);
        User owner1 = userRepository.findByUsername("owner1").orElse(null);
        if (owner1 == null) {
            owner1 = new User();
            owner1.setFirstName("owner1");
//        adminUser.setId((long) 1);
            owner1.setUsername("owner1");
            owner1.setPassword(passwordEncoder.encode("x"));
            owner1.setEmail("owner1@gmail.com");
            owner1.setRoles(userRoles);
            userRepository.save(owner1);
        }

        User owner2 = userRepository.findByUsername("owner2").orElse(null);
        if (owner2 == null) {
            owner2 = new User();
            owner2.setFirstName("owner2");
//        adminUser.setId((long) 1);
            owner2.setUsername("owner2");
            owner2.setPassword(passwordEncoder.encode("x"));
            owner2.setEmail("owner2@gmail.com");
            owner2.setRoles(userRoles);
            userRepository.save(owner2);
        }


        User owner3 = userRepository.findByUsername("owner3").orElse(null);
        if (owner3 == null) {
            owner3 = new User();
            owner3.setFirstName("owner3");
//        adminUser.setId((long) 1);
            owner3.setUsername("owner3");
            owner3.setPassword(passwordEncoder.encode("x"));
            owner3.setEmail("owner3@gmail.com");
            owner3.setRoles(userRoles);
            userRepository.save(owner3);
        }

    }
}