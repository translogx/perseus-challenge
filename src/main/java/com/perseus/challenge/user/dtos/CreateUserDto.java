package com.perseus.challenge.user.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserDto {

    private String lastName;

    private String firstName;

    private List<String> emails;

    private List<String> phoneNumbers;
}
