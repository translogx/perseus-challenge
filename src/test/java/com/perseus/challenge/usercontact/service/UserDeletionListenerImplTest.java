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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserDeletionListenerImplTest {


    @InjectMocks
    private UserDeletionListenerImpl userDeletionListener;

    @Mock
    private UserContactService userContactService;

    @Test
    void onDeleteUser() {
        userDeletionListener.onDeleteUser(anyList(), anyList());

        verify(userContactService).deleteUserContacts(anyList(), anyList());
    }
}