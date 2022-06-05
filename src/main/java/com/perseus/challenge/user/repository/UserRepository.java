package com.perseus.challenge.user.repository;

import com.perseus.challenge.user.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select a from UserEntity a where ((a.firstName = :firstname and a.lastName = :lastname) or a.firstName = :firstname or a.lastName = :lastname)")
    List<UserEntity> findByName(String firstname, String lastname);


}
