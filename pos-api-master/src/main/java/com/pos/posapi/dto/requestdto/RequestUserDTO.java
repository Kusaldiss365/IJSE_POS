package com.pos.posapi.dto.requestdto;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestUserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String userRoleId;
}
