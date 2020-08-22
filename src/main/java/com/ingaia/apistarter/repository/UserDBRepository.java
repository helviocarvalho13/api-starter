package com.ingaia.apistarter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingaia.apistarter.model.UserDB;

@Repository
public interface UserDBRepository extends JpaRepository<UserDB, Long>{

	Optional<UserDB> findByEmail(String email);

}
