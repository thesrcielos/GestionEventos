package com.eventmanagement.user.controller;

import com.eventmanagement.user.dto.UserRequest;
import com.eventmanagement.user.dto.UserRequestUpdate;
import com.eventmanagement.user.dto.UserResponse;
import com.eventmanagement.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.saveUser(userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponse updateUser(@RequestBody UserRequestUpdate userRequestUpdate){
        return userService.updateUser(userRequestUpdate);
    }

    @GetMapping("/verify/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean existUserById(@PathVariable Long id){
        return userService.existsUserById(id);
    }
}
