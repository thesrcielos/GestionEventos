package com.eventmanagement.user.service;

import com.eventmanagement.user.dto.UserRequest;
import com.eventmanagement.user.dto.UserRequestUpdate;
import com.eventmanagement.user.dto.UserResponse;
import com.eventmanagement.user.model.User;
import com.eventmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final PasswordEncoder passwordEncoder;

    public UserResponse saveUser(UserRequest userRequest){
        User user = modelMapper.map(userRequest,User.class);
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return modelMapper.map(user,UserResponse.class);
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

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user->modelMapper.map(user,UserResponse.class)).toList();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
