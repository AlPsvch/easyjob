package com.easyjob.jobmanager.service.user;

import com.easyjob.jobmanager.dao.user.UserRepository;
import com.easyjob.jobmanager.entity.user.User;
import com.easyjob.jobmanager.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User addUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " was not found"));
    }

    public User findUser(String username) {
        return userRepository.getUserByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username: " + username + " was not found"));
    }
}
