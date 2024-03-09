package com.pos.posapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO implements SuperDTO {

    private String userId;

    private boolean activeState;

    private String email;

    private String firstName;

    private String lastName;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    private String password;

    private Date registerDate;

    private String username;

    private String prefix;

    private int otp;

    private UserRoleDTO userRole;

    public UserDTO(boolean activeState, String email, String firstName, String lastName, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, String password, Date registerDate, String username, String prefix, int otp, UserRoleDTO userRole) {
        this.activeState = activeState;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.password = password;
        this.registerDate = registerDate;
        this.username = username;
        this.prefix = prefix;
        this.otp = otp;
        this.userRole = userRole;
    }
}
