package com.eventmanagement.apigateway.service;

import com.eventmanagement.apigateway.dto.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeycloakService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchByUsername(String username);
    String createUser(UserDTO userDTO);
    void deleteUser(String userId);
}
