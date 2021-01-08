package com.tacticalwolves.provider.controllers;

import com.tacticalwolves.provider.entity.UserRequest;
import com.tacticalwolves.provider.service.RoleService;
import com.tacticalwolves.provider.service.UserService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
class UserController{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<UserRepresentation> findAll() {
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public UserRepresentation findById(@PathVariable String id) {
       return userService.findById(id);
    }
    @GetMapping("/username/{username}")
    public List<UserRepresentation> findByUsername(@PathVariable String username) {
       return userService.findByUsername(username);
    }
    @PostMapping
    public ResponseEntity<URI> create(@RequestBody UserRequest userRequest)
    {
        var response = userService.create(userRequest);
        if (response.getStatus() != 201)
            throw new RuntimeException("User was not created");
        return ResponseEntity.created(response.getLocation()).build();
    }
    @PostMapping("/{userId}/group/{groupId}")
    public void assignToGroup(
            @PathVariable String userId ,
            @PathVariable String groupId
    ) {
        userService.assignToGroup(userId, groupId);
    }
    @PostMapping("/{userId}/role/{roleName}")
    public void assignRole(
            @PathVariable String userId,
            @PathVariable String roleName
    ) {
        var role = roleService.findByName(roleName);
        userService.assignRole(userId, role);
    }
}
