package com.tacticalwolves.provider.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
        @Autowired
        private Keycloak keycloak;
        private String realmString = "TacticalWolves";

    public void create(String name) {
        var role = new RoleRepresentation();
        role.setName(name);
        keycloak
                .realm(realmString)
                .roles()
                .create(role);
    }
    public List<RoleRepresentation> findAll() {
      return (  keycloak
                .realm(realmString)
                .roles()
                .list());
    }
    public RoleRepresentation findByName(String roleName) {
        return (keycloak
                .realm(realmString)
                .roles()
                .get(roleName)
                .toRepresentation());
    }
}
