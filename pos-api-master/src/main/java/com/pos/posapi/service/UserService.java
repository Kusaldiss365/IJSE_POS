package com.pos.posapi.service;

import com.pos.posapi.dto.requestdto.RequestUserSaveDto;
import com.pos.posapi.dto.responsedto.ResponseUserDataDTO;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {

    void initializeUser() throws IOException;

    CommonResponseDTO verifyUser(int otp, String email);

    ResponseUserDataDTO getAllUserData(String token);

    CommonResponseDTO createUser(RequestUserSaveDto userDTO);

    List<ResponseUserDataDTO> getAll();

    CommonResponseDTO deleteUser(String id);
}
