package com.perseus.challenge.usercontact.controllers;

import com.perseus.challenge.usercontact.Interface.UserContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact/{userId}")
public class UserContactController {

    private final UserContactService userContactService;

    @PostMapping(value = "/add-email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addEmail(@PathVariable(name = "userId") int userId,
                              @RequestParam(name = "email") String email){
        userContactService.addEmail(userId, email);

    }

    @PostMapping(value = "/add-phone-number", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPhoneNumber(@PathVariable(name = "userId") int userId,
                              @RequestParam(name = "phoneNumber") String phoneNumber){
        userContactService.addPhoneNumber(userId, phoneNumber);

    }

    @PutMapping(value = "/email/{emailId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEmail(@PathVariable(name = "userId") int userId,
                            @PathVariable(name = "emailId") int emailId,
                         @RequestParam(name = "email") String email){
        userContactService.updateEmail(userId, emailId, email);

    }

    @PutMapping(value = "/phone-number/{phoneNumberId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePhoneNumber(@PathVariable(name = "userId") int userId,
                                  @PathVariable(name = "phoneNumberId") int phoneNumberId,
                               @RequestParam(name = "phoneNumber") String phoneNumber){
        userContactService.updatePhoneNumber(userId, phoneNumberId, phoneNumber);

    }
}
