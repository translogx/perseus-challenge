package com.perseus.challenge.user.service;

import com.perseus.challenge.exceptionHandlers.NotFoundException;
import com.perseus.challenge.exceptionHandlers.ServerRuntimeException;
import com.perseus.challenge.user.models.UserEntity;
import com.perseus.challenge.user.repository.UserRepository;
import com.perseus.challenge.usercontact.Interface.UserCreationListener;
import com.perseus.challenge.usercontact.Interface.UserDeletionListener;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static com.perseus.challenge.testutils.TestUtil.getCreateUserDto;
import static com.perseus.challenge.testutils.TestUtil.getUserEntity;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private  UserCreationListener userCreationListener;

    @Mock
    private  UserDeletionListener userDeletionListener;

    @Mock
    private  UserRepository userRepository;

    @Before
    public void setUp(){
    }

    @Test
    void getUserById() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getUserEntity()));

        userService.getUserById(anyInt());

        verify(userRepository).findById(anyInt());

    }

    @Test
    void getUserById_whenUserNotFound()  {
        assertThatThrownBy(() -> userService.getUserById(anyInt()))
                .isInstanceOf(NotFoundException.class);

        verify(userRepository).findById(anyInt());
    }

    @Test
    void getUserByName() {
        when(userRepository.findByName(anyString(), anyString())).thenReturn(Arrays.asList(getUserEntity()));

        userService.getUserByName(anyString(), anyString());

        verify(userRepository).findByName(anyString(), anyString());
    }

    @Test
    void getUserByName_whenUserNotFound() {
        when(userRepository.findByName(anyString(), anyString())).thenReturn(Arrays.asList(getUserEntity()));

        userService.getUserByName(anyString(), anyString());

        verify(userRepository).findByName(anyString(), anyString());
    }

    @Test
    void deleteUser() {

        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getUserEntity()));
        doNothing().when(userRepository).delete(any(UserEntity.class));
        doNothing().when(userDeletionListener).onDeleteUser(anyList(), anyList());

        userService.deleteUser(anyInt());

        verify(userDeletionListener).onDeleteUser(anyList(), anyList());
    }

    @Test
    void deleteUser_whenServiceFailToSaveToDb() {

        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getUserEntity()));
        doThrow(new NotFoundException("failed to delete data"))
                .when(userRepository).delete(any(UserEntity.class));

        assertThatThrownBy(()-> userService.deleteUser(anyInt()))
                .isInstanceOf(ServerRuntimeException.class);

    }

    @Test
    void createNewUser() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getUserEntity()));
        doNothing().when(userCreationListener).onCreateUser(anyInt(), anyList(), anyList());
        when(userRepository.save(any())).thenReturn(getUserEntity());

        userService.createNewUser(getCreateUserDto());

        verify(userCreationListener).onCreateUser(anyInt(), anyList(), anyList());

    }

}