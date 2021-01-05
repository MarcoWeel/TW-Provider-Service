package com.tacticalwolves.provider.controllers;

import com.tacticalwolves.provider.service.RoleService;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
class RoleController{
    @Autowired
    private RoleService RoleService;

    @GetMapping
    public List<RoleRepresentation> findAll() {
       return RoleService.findAll();
    }
    @PostMapping
    public void createRole(@RequestParam String name) {
        RoleService.create(name);
    }
}