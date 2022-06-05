package com.perseus.challenge.usercontact.Interface;

import java.util.List;

public interface UserContactService {

    void addPhoneNumber(int id, String phoneNumber);

    void addEmail(int id, String email);

    void updatePhoneNumber(int userId, int phoneNumberId, String updatedPhoneNumber);

    void updateEmail(int userId, int emailId, String updatedEmail);

    void deleteUserContacts(List<Integer> userEmailIds, List<Integer> userPhoneNumberIds);
}
