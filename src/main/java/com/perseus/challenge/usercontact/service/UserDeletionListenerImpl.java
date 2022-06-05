package com.perseus.challenge.usercontact.service;

import com.perseus.challenge.usercontact.Interface.UserDeletionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserDeletionListenerImpl implements UserDeletionListener {

    private final UserContactServiceImpl userContactService;

    @Override
    public void onDeleteUser(List<Integer> userEmailIds, List<Integer> userPhoneNumberIds) {
        userContactService.deleteUserContacts(userEmailIds, userPhoneNumberIds);
    }
}
