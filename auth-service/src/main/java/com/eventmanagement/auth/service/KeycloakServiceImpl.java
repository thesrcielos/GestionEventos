package com.eventmanagement.auth.service;

import com.eventmanagement.apigateway.dto.UserDTO;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {
    private final KeycloakUtilsService keycloakUtilsService;

    @Override
    public List<UserRepresentation> findAllUsers() {
        return keycloakUtilsService.getUserResource().list();
    }

    @Override
    public List<UserRepresentation> searchByUsername(String username) {
        return keycloakUtilsService.getUserResource().searchByUsername(username,true);
    }

    @Override
    public String createUser(UserDTO userDTO) {
        int status;
        UsersResource usersResource = keycloakUtilsService.getUserResource();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userDTO.getUsername());
        userRepresentation.setEmail(userDTO.getEmail());
        userRepresentation.setFirstName(userDTO.getFirstName());
        userRepresentation.setLastName(userDTO.getLastName());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        Response response = usersResource.create(userRepresentation);

        status = response.getStatus();
        System.out.println(status);
        if(status == 409){
            log.info("User already exists");
            return "User already exists";
        }else if(status != 201){
            log.info("Error creating user");
            return "Error creating user";
        }

        log.info("User created successfully");
        String path = response.getLocation().getPath();
        String userId = path.substring(path.lastIndexOf('/') + 1);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(OAuth2Constants.PASSWORD);
        credentialRepresentation.setValue(userDTO.getPassword());
        usersResource.get(userId).resetPassword(credentialRepresentation);

        RealmResource realmResource = keycloakUtilsService.getRealmResource();
        List<RoleRepresentation> rolesRepresentations;
        Set<String> userRoles = userDTO.getRoles();
        if(userRoles == null || userRoles.isEmpty()){
            rolesRepresentations = List.of(realmResource.roles().get("app_user").toRepresentation());
        }else{
            rolesRepresentations = realmResource.roles().list().stream()
                    .filter(role-> userRoles.stream().anyMatch(userRole->userRole.equalsIgnoreCase(role.getName())))
                    .collect(Collectors.toList());
        }

        usersResource.get(userId).roles().realmLevel().add(rolesRepresentations);
        return "User successfully created";
    }

    @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = keycloakUtilsService.getUserResource();
        usersResource.get(userId).remove();
    }
}
