package com.easyjob.jobmanager.dao.user;

import com.easyjob.jobmanager.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsername(String username);
}
