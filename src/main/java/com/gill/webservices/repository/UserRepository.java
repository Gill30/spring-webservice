package com.gill.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gill.webservices.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
