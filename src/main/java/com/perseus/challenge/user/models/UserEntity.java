package com.perseus.challenge.user.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.perseus.challenge.usercontact.models.EmailEntity;
import com.perseus.challenge.usercontact.models.PhoneNumberEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "users")
@JsonIgnoreProperties( ignoreUnknown = true)
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String lastName;

    private String firstName;

    @OneToMany(mappedBy = "user",  fetch = FetchType.LAZY)
    private List<EmailEntity> emails ;

    @OneToMany(mappedBy = "user",  fetch = FetchType.LAZY)
    private List<PhoneNumberEntity> phoneNumbers;

}
