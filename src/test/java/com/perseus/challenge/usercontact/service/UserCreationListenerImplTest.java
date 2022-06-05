package com.perseus.challenge.usercontact.service;

import com.perseus.challenge.testutils.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@Slf4j
@ExtendWith(MockitoExtension.class)
class UserCreationListenerImplTest {

    @InjectMocks
    private UserCreationListenerImpl userCreationListener;

    @Mock
    private UserContactServiceImpl userContactService;

    @Test
    void onCreateUser() {
        List<String> emails = Arrays.asList("email@Aol.com", "email@perseus.com");
        List<String> phones = Arrays.asList("1232423", "009762");

        userCreationListener.onCreateUser(TestUtil.getUserEntity().getId(), emails, phones);

        verify(userContactService, times(phones.size())).addPhoneNumber(anyInt(), anyString());
    }
}