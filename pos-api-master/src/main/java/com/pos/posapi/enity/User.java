package com.pos.posapi.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "active_state",  columnDefinition = "TINYINT")
    private boolean activeState;

    @Column(length = 250, name = "email")
    private String email;

    @Column(length = 45, name = "first_name")
    private String firstName;

    @Column(length = 45, name = "last_name")
    private String lastName;

    @Column(name = "is_account_non_expired",  columnDefinition = "TINYINT")
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked",  columnDefinition = "TINYINT")
    private boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired",  columnDefinition = "TINYINT")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled",  columnDefinition = "TINYINT")
    private boolean isEnabled;

    @Column(length = 250, name = "password")
    private String password;

    @Temporal(TemporalType.DATE)
    @Column( name = "register_date")
    private Date registerDate;

    @Column(length = 100, name = "user_name", unique = true)
    private String username;

    @Column(length = 16, name = "prefix")
    private String prefix;

    @Column(length = 10, name = "otp")
    private int otp;


    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private Set<CartItem> cartItem;


}
