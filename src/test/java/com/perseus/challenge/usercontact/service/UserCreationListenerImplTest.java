package com.perseus.challenge.usercontact.service;

import com.perseus.challenge.testutils.TestUtil;
import com.perseus.challenge.user.models.UserEntity;
import com.perseus.challenge.usercontact.Interface.UserContactService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@Slf4j
@ExtendWith(MockitoExtension.class)
class UserCreationListenerImplTest {

    @InjectMocks
    private UserCreationListenerImpl userCreationListener;

    @Mock
    private UserContactService userContactService;

    @Test
    void onCreateUser() {
        List<String> emails = Arrays.asList("email@Aol.com", "email@perseus.com");
        List<String> phones = Arrays.asList("1232423", "009762");

        userCreationListener.onCreateUser(TestUtil.getUserEntity().getId(), emails, phones);

        verify(userContactService, times(phones.size())).addPhoneNumber(anyInt(), anyString());
    }
}