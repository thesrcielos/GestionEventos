package com.eventmanagement.user.service;

import com.eventmanagement.user.dto.UserRequest;
import com.eventmanagement.user.dto.UserResponse;
import com.eventmanagement.user.model.User;
import com.eventmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public UserResponse saveUser(UserRequest userRequest){
        User user = modelMapper.map(userRequest,User.class);
        userRepository.save(user);
        return modelMapper.map(user,UserResponse.class);
    }

}
