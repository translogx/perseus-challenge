package com.perseus.challenge.testutils;

import com.perseus.challenge.user.dtos.CreateUserDto;
import com.perseus.challenge.user.models.UserEntity;
import com.perseus.challenge.usercontact.models.EmailEntity;
import com.perseus.challenge.usercontact.models.PhoneNumberEntity;

import java.util.Arrays;

public class TestUtil {

    public static UserEntity getUserEntity(){
        EmailEntity email1 = new EmailEntity();
        EmailEntity email2 = new EmailEntity();
        email1.setId(1);
        email1.setMail("vincent@perseus.de");
        email2.setMail("vincent@aol.com");
        email1.setId(2);

        PhoneNumberEntity phone1 = new PhoneNumberEntity();
        PhoneNumberEntity phone2 = new PhoneNumberEntity();
        phone1.setPhoneNumber("1234567");
        phone1.setId(1);
        phone2.setPhoneNumber("8900001");
        phone2.setId(2);


        return UserEntity.builder()
                .emails(Arrays.asList(email1,email2))
                .phoneNumbers(Arrays.asList(phone1,phone2))
                .id(1)
                .build();
    }

    public static CreateUserDto getCreateUserDto(){
        return CreateUserDto.builder()
                .lastName("enyinwa")
                .firstName("vincent")
                .emails(Arrays.asList("email@1.co"))
                .phoneNumbers(Arrays.asList("123456"))
                .build();
    }
}
