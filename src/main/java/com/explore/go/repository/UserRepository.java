package com.explore.go.repository;

import com.explore.go.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmailAddress(String emailAddress);
    User findUserByEmailAddressAndPassword(String username, String password);

}
