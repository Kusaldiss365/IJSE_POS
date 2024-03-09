package com.pos.posapi;

import com.pos.posapi.service.CartService;
import com.pos.posapi.service.UserRoleService;
import com.pos.posapi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PosApiApplication implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final CartService cartService;

    public PosApiApplication(UserService userService, UserRoleService userRoleService, CartService cartService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.cartService = cartService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PosApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRoleService.initializeUserRoles();
        userService.initializeUser();
        cartService.initializeCart();
    }
}
