package com.easyjob.jobmanager.service.auth;

import com.easyjob.jobmanager.entity.user.User;
import com.easyjob.jobmanager.exception.UserNotFoundException;
import com.easyjob.jobmanager.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service(value = "userDetailsService")
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImplementation(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        User user = userService.findUser(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<GrantedAuthority> getAuthority(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

}
