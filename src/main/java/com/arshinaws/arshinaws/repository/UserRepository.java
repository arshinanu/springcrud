package com.arshinaws.arshinaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arshinaws.arshinaws.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
