package com.perseus.challenge.usercontact.Interface;

import com.perseus.challenge.user.models.UserEntity;

import java.util.List;

public interface UserCreationListener {

    void onCreateUser(int userId, List<String> userEmails, List<String> userPhoneNumbers);

}
