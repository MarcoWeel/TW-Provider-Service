package com.tacticalwolves.provider.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClientConfig {

    @Bean
    public Keycloak getKeycloak(){
        String serverUrl = "https://84.86.167.197:8082/auth";
        String realm = "TacticalWolves";
        String clientId = "TacticalWolves-Admin";
        String clientSecret = "95b22d8a-1696-4314-824e-192947bf39ae";
        return KeycloakBuilder.builder().resteasyClient(new ResteasyClientBuilder().disableTrustManager().build())
                .serverUrl(serverUrl) //
                .realm(realm) //
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS) //
                .clientId(clientId) //
                .clientSecret(clientSecret)
                .build();
    }
}