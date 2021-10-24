package com.project.real.controller;

import com.project.real.service.RoleService;
import com.project.real.model.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 
@RestController
@RequestMapping(value = "roles")
public class RoleController extends com.project.real.core.BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public Role createRole(@RequestBody Role role){
        return  roleService.createNewRole(role);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Role> getAll(){
        return  roleService.getAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Role updateRole(@PathVariable("id") Long id,@RequestBody Role role){
        return  roleService.updateRole(id, role);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void updateRole(@PathVariable("id") Long id){
        roleService.deleteRole(id);
    }
}
