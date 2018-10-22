package com.undefined.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.undefined.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}