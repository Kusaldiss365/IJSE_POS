package com.pos.posapi.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserSaveDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
