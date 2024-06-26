package com.eventmanagement.auth.service;

import com.eventmanagement.auth.dto.TokenResponse;
import com.eventmanagement.auth.dto.UserDTO;
import com.eventmanagement.auth.dto.UserRequest;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeycloakService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchByUsername(String username);
    String createUser(UserDTO userDTO);
    void deleteUser(String userId);

    TokenResponse logIn(UserRequest userRequest);
}
