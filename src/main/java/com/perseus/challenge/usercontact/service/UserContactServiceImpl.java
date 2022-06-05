package com.perseus.challenge.usercontact.service;

import com.perseus.challenge.exceptionHandlers.NotFoundException;
import com.perseus.challenge.user.models.UserEntity;
import com.perseus.challenge.user.repository.UserRepository;
import com.perseus.challenge.usercontact.models.EmailEntity;
import com.perseus.challenge.usercontact.models.PhoneNumberEntity;
import com.perseus.challenge.usercontact.repository.EmailRepository;
import com.perseus.challenge.usercontact.repository.PhoneNumberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserContactServiceImpl {

    private final UserRepository userRepository;

    private final EmailRepository emailRepository;

    private final PhoneNumberRepository phoneNumberRepository;

    @Transactional
    public void addPhoneNumber(int id, String phoneNumber) {
        UserEntity user = getUserById(id);

        PhoneNumberEntity newPhoneNumber = new PhoneNumberEntity();
        newPhoneNumber.setPhoneNumber(phoneNumber);
        newPhoneNumber.setUser(user);
        phoneNumberRepository.save(newPhoneNumber);
    }

    @Transactional
    public void addEmail(int id, String email) {
       UserEntity user = getUserById(id);
        EmailEntity newEmail = new EmailEntity();
        newEmail.setMail(email);
        newEmail.setUser(user);

        emailRepository.save(newEmail);

    }

    public void updatePhoneNumber(int userId, int phoneNumberId, String updatedPhoneNumber) {
        UserEntity user = getUserById(userId);
       PhoneNumberEntity phoneNumber = user.getPhoneNumbers().stream().filter(e -> e.getId() == phoneNumberId).findFirst()
               .orElseThrow(() -> new NotFoundException(String.format("Phone number with id %s does not exist for this user", phoneNumberId)));

       phoneNumber.setPhoneNumber(updatedPhoneNumber);
       phoneNumberRepository.save(phoneNumber);
    }

    public void updateEmail(int userId, int emailId, String updatedEmail) {
        UserEntity user = getUserById(userId);
        EmailEntity email = user.getEmails().stream().filter(e -> e.getId() == emailId).findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Email with id %s does not exist for this user", emailId)));

        email.setMail(updatedEmail);
        emailRepository.save(email);
    }

    public void deleteUserContacts(List<Integer> userEmailIds, List<Integer> userPhoneNumberIds) {
        emailRepository.deleteAllById(userEmailIds);
        phoneNumberRepository.deleteAllById(userPhoneNumberIds);
    }

    private UserEntity getUserById(int userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }


}
