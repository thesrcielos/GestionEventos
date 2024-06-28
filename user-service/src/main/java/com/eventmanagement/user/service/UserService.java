package com.eventmanagement.user.service;

import com.eventmanagement.user.dto.UserRequest;
import com.eventmanagement.user.dto.UserRequestUpdate;
import com.eventmanagement.user.dto.UserResponse;
import com.eventmanagement.user.model.User;
import com.eventmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final PasswordEncoder passwordEncoder;
    private final RestClient restClient;

    public UserResponse saveUser(UserRequest userRequest) throws Exception {
        if (!createUserInKeycloak(userRequest)){
            throw new Exception("Error creating user");
        }
        User user = modelMapper.map(userRequest,User.class);
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return modelMapper.map(user,UserResponse.class);
    }

    private boolean createUserInKeycloak(UserRequest userRequest){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username",userRequest.getName());
        requestBody.put("email", userRequest.getEmail());
        requestBody.put("firstName", userRequest.getFirstName());
        requestBody.put("lastName", userRequest.getLastName());
        requestBody.put("password", userRequest.getPassword());
        ResponseEntity<String> response = restClient.post()
                .uri("http://localhost:8083/api/auth/create")
                //.header("Content-Type", "application/x-www-form-urlencoded")
                .body(requestBody)
                .retrieve()
                .toEntity(String.class);
        return Objects.equals(response.getBody(), "User successfully created");
    }
    public UserResponse updateUser(UserRequestUpdate userRequest){
        Long id = userRequest.getId();
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User with id "+ id +" not found"));
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        userRepository.save(user);
        return modelMapper.map(user,UserResponse.class);
    }

    public UserResponse findUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return modelMapper.map(user,UserResponse.class);
    }

    public User findUser(Long id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user->modelMapper.map(user,UserResponse.class)).toList();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public boolean existsUserById(Long id){
        return userRepository.existsById(id);
    }

}
