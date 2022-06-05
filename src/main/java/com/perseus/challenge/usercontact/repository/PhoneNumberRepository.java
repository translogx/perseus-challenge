package com.perseus.challenge.usercontact.repository;

import com.perseus.challenge.usercontact.models.PhoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Integer> {
}
