package com.tacticalwolves.provider.service;

import com.tacticalwolves.provider.entity.UserRequest;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Service
public
class UserService{
    @Autowired
    private Keycloak keycloak;
    private String realmString = "TacticalWolves";

    public List<UserRepresentation> findAll() {
       return(keycloak
                .realm(realmString)
                .users()
                .list());
    }
    public List<UserRepresentation> findByUsername(String username) {
       return( keycloak
                .realm(realmString)
                .users()
                .search(username));
    }
    public UserRepresentation findById(String id) {
      return (  keycloak
                .realm(realmString)
                .users()
                .get(id)
                .toRepresentation());
    }
    public void assignToGroup(String userId, String groupId) {
        keycloak
                .realm(realmString)
                .users()
                .get(userId)
                .joinGroup(groupId);
    }
    public void assignRole(String userId, RoleRepresentation roleRepresentation) {
        keycloak
                .realm(realmString)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(Arrays.asList(roleRepresentation));
    }
    public Response create(UserRequest request)  {
        var password = preparePasswordRepresentation(request.password);
        var user = prepareUserRepresentation(request, password);
        return keycloak
                .realm(realmString)
                .users()
                .create(user);
    }
    private CredentialRepresentation preparePasswordRepresentation(String password){
        var cR = new CredentialRepresentation();
        cR.setTemporary(false);
        cR.setType(CredentialRepresentation.PASSWORD);
        cR.setValue(password);
        return cR;
    }
    private UserRepresentation prepareUserRepresentation(
            UserRequest request,
            CredentialRepresentation cR
    ) {
        var newUser = new UserRepresentation();
        newUser.setUsername(request.username);
        newUser.setCredentials(Arrays.asList(cR));
        newUser.setEnabled(true);
        return newUser;
    }
}
