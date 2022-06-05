package com.perseus.challenge.user.interfaces;

import com.perseus.challenge.user.dtos.CreateUserDto;
import com.perseus.challenge.user.models.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserEntity createNewUser(CreateUserDto user);

    UserEntity getUserById(int id);

    Map<String, List<UserEntity>> getUserByName(String firstName, String lastName);

    void deleteUser (int userId);

}
