package com.pos.posapi.controller;

import com.pos.posapi.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/user-role")
@CrossOrigin
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

}
