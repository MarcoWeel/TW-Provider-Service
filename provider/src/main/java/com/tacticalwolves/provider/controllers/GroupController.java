package com.tacticalwolves.provider.controllers;

import com.tacticalwolves.provider.service.GroupService;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@CrossOrigin("*")
class GroupController{
    @Autowired
    private GroupService GroupService;

    @GetMapping
    public List<GroupRepresentation> findAll() {
        return GroupService.findAll();
    }
    @PostMapping
    public void createRole(@RequestParam String name) {
        GroupService.create(name);
    }
}