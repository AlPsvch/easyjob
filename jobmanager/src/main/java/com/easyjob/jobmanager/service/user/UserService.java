package com.easyjob.jobmanager.service.user;

import com.easyjob.jobmanager.dao.user.UserRepository;
import com.easyjob.jobmanager.entity.user.User;
import com.easyjob.jobmanager.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User addUser(User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            throw new IllegalArgumentException("Username should not be empty");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            throw new IllegalArgumentException("Password should not be empty");
        }
        if(!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email not valid");
        }

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

    private boolean isValidEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
