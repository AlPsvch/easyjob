package com.easyjob.jobmanager.controller.auth;

import com.easyjob.jobmanager.dto.user.UserDto;
import com.easyjob.jobmanager.entity.user.LoginUserDto;
import com.easyjob.jobmanager.entity.user.User;
import com.easyjob.jobmanager.service.auth.AuthService;
import com.easyjob.jobmanager.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody User user) {
        String password = user.getPassword();
        User registeredUser = userService.addUser(user);
        String token = authService.login(user.getUsername(), password);
        return new ResponseEntity<>(convertToDto(registeredUser, token), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginUserDto loginUserDto) {
        String token = authService.login(loginUserDto.getUsername(), loginUserDto.getPassword());
        User user = userService.findUser(loginUserDto.getUsername());
        return new ResponseEntity<>(convertToDto(user, token), HttpStatus.OK);
    }

    private UserDto convertToDto(User user, String token) {
        return new UserDto(user, token);
    }
}
