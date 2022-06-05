package com.perseus.challenge.usercontact.service;

import com.perseus.challenge.usercontact.Interface.UserCreationListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserCreationListenerImpl implements UserCreationListener {

    private final UserContactServiceImpl userContactService;

    @Override
    public void onCreateUser(int userId, List<String> userEmails, List<String> userPhoneNumbers) {
        userEmails.forEach(email -> userContactService.addEmail(userId, email));
        userPhoneNumbers.forEach(phoneNumber -> userContactService.addPhoneNumber(userId, phoneNumber));
    }
}
