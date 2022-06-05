package com.perseus.challenge.user.service;

import com.perseus.challenge.exceptionHandlers.NotFoundException;
import com.perseus.challenge.exceptionHandlers.RequestException;
import com.perseus.challenge.exceptionHandlers.ServerRuntimeException;
import com.perseus.challenge.user.dtos.CreateUserDto;
import com.perseus.challenge.user.interfaces.UserService;
import com.perseus.challenge.user.models.UserEntity;
import com.perseus.challenge.user.repository.UserRepository;
import com.perseus.challenge.usercontact.Interface.UserCreationListener;
import com.perseus.challenge.usercontact.Interface.UserDeletionListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserCreationListener userCreationListener;

    private final UserDeletionListener userDeletionListener;

    private final UserRepository userRepository;

    @Override
    public UserEntity createNewUser(CreateUserDto user) {

        UserEntity newUser = UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

        userRepository.save(newUser);

        userCreationListener.onCreateUser(newUser.getId(), user.getEmails(), user.getPhoneNumbers());
        return  userRepository.findById(newUser.getId())
                .orElseThrow(() -> new ServerRuntimeException("Unknown error. Please try again"));
    }

    @Override
    public UserEntity getUserById(int id) {
        return  userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public Map<String, List<UserEntity>> getUserByName(String firstName, String lastName) {
        Map<String, List<UserEntity>> response = new HashMap<>();
        response.put("result", userRepository.findByName(firstName, lastName));
        return response;
    }


    @Transactional
    @Override
    public void deleteUser(int userId) {
        List<Integer> userEmailIds = new ArrayList<>();
        List<Integer> userPhoneNumberIds = new ArrayList<>();
        try {
            UserEntity user = getUserById(userId);

            user.getEmails().forEach(emailEntity -> userEmailIds.add(emailEntity.getId()));
            user.getPhoneNumbers().forEach(phoneNumberEntity -> userPhoneNumberIds.add(phoneNumberEntity.getId()));

            userRepository.delete(user);
            userDeletionListener.onDeleteUser(userEmailIds, userPhoneNumberIds);
        }catch (Exception e){
            throw new ServerRuntimeException("Failed to delete user: "+ e.getMessage());
        }

    }
}
