package com.perseus.challenge.usercontact.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserDeletionListenerImplTest {


    @InjectMocks
    private UserDeletionListenerImpl userDeletionListener;

    @Mock
    private UserContactServiceImpl userContactService;

    @Test
    void onDeleteUser() {
        userDeletionListener.onDeleteUser(anyList(), anyList());

        verify(userContactService).deleteUserContacts(anyList(), anyList());
    }
}