package com.perseus.challenge.usercontact.repository;

import com.perseus.challenge.usercontact.models.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailEntity, Integer> {

}
