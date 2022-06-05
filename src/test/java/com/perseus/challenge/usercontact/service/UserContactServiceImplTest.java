package com.perseus.challenge.usercontact.service;

import com.perseus.challenge.exceptionHandlers.NotFoundException;
import com.perseus.challenge.testutils.TestUtil;
import com.perseus.challenge.user.models.UserEntity;
import com.perseus.challenge.user.repository.UserRepository;
import com.perseus.challenge.usercontact.models.EmailEntity;
import com.perseus.challenge.usercontact.models.PhoneNumberEntity;
import com.perseus.challenge.usercontact.repository.EmailRepository;
import com.perseus.challenge.usercontact.repository.PhoneNumberRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserContactServiceImplTest {

    private final String EMAIL = "anemail@google.com";

    private final String PHONE = "09876543";

    @InjectMocks
    private UserContactServiceImpl userContactService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @Test
    void addPhoneNumber() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(TestUtil.getUserEntity()));

        userContactService.addPhoneNumber(1, PHONE);

        verify(phoneNumberRepository).save(any(PhoneNumberEntity.class));
    }

    @Test
    void addEmail() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(TestUtil.getUserEntity()));

        userContactService.addEmail(1, EMAIL);

        verify(emailRepository).save(any());
    }

    @Test
    void updatePhoneNumber() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(TestUtil.getUserEntity()));

        userContactService.updatePhoneNumber(1, 1, PHONE);

        verify(phoneNumberRepository).save(any(PhoneNumberEntity.class));
    }

    @Test
    void updatePhoneNumber_whenPhoneNumberIdDoesNotBelongToUser() throws NotFoundException {

        Assertions.assertThrows(NotFoundException.class, () -> userContactService.updatePhoneNumber(1, 1000, PHONE));

    }

    @Test
    void updateEmail() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(TestUtil.getUserEntity()));

        userContactService.updateEmail(1, 2, EMAIL);

        verify(emailRepository).save(any(EmailEntity.class));
    }

    @Test
    void updateEmail_whenEmailIdDoesNotBelongToUser() {

        Assertions.assertThrows(NotFoundException.class, () -> userContactService.updateEmail(1, 10000, EMAIL));

    }

    @Test
    void deleteUserContacts() {
        List<Integer> emailIds = TestUtil.getUserEntity().getEmails().stream().map(EmailEntity::getId).collect(Collectors.toList());
        List<Integer> phoneNumberIds = TestUtil.getUserEntity().getPhoneNumbers().stream().map(PhoneNumberEntity::getId).collect(Collectors.toList());

        userContactService.deleteUserContacts(emailIds, phoneNumberIds);

        verify(emailRepository).deleteAllById(anyList());
        verify(phoneNumberRepository).deleteAllById(anyList());
    }
}