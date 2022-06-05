package com.perseus.challenge.user.controllers;

import com.perseus.challenge.user.dtos.CreateUserDto;
import com.perseus.challenge.user.models.UserEntity;
import com.perseus.challenge.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/create")
    public UserEntity createUser(@RequestBody CreateUserDto request){
        return userService.createNewUser(request);
    }

    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable("userId") int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/find")
    public Map<String, List<UserEntity>> getUserByName(@RequestParam(name = "firstName", required = false) String firstName,
                                                       @RequestParam(name = "lastName", required = false) String lastName) {
        return userService.getUserByName(firstName, lastName);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
    }
}
