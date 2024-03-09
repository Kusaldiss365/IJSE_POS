package com.pos.posapi.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {
    private String userId;
    private boolean activeState;
    private String email;
    private String firstName;
    private String lastName;
    private String roleName;
}
