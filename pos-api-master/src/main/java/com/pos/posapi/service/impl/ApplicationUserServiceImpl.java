package com.pos.posapi.service.impl;


import com.pos.posapi.auth.ApplicationUser;
import com.pos.posapi.enity.User;
import com.pos.posapi.repo.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.pos.posapi.security.ApplicationUserRole.*;

@Service
public class ApplicationUserServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;


    public ApplicationUserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepo.findByUsername(username);
        if (byUsername == null) {
            throw new UsernameNotFoundException(String.format("username %s not found", username));
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        switch (byUsername.getUserRole().getRoleName()) {

            case "ADMIN":
                grantedAuthorities = ADMIN.getGrantedAuthorities();
                break;
            case "MANAGER":
                grantedAuthorities = MANAGER.getGrantedAuthorities();
                break;
            case "CUSTOMER":
                grantedAuthorities = CUSTOMER.getGrantedAuthorities();
                break;

        }

        ApplicationUser user = new ApplicationUser(
                byUsername.getPassword(),
                byUsername.getUsername(),
                grantedAuthorities,
                byUsername.isAccountNonExpired(),
                byUsername.isAccountNonLocked(),
                byUsername.isCredentialsNonExpired(),
                byUsername.isEnabled()
        );
        return user;
    }
}
