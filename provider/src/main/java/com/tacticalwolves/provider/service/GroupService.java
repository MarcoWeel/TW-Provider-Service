package com.tacticalwolves.provider.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private Keycloak keycloak;
    private String realmString = "TacticalWolves";

    public void create(String name) {
        var group = new GroupRepresentation();
        group.setName(name);
        keycloak
                .realm(realmString)
                .groups()
                .add(group);
    }
    public List<GroupRepresentation> findAll() {
       return( keycloak
                .realm(realmString)
                .groups()
                .groups());
    }
}
