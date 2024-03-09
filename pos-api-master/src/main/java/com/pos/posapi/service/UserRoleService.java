package com.pos.posapi.service;

import com.pos.posapi.dto.responsedto.ResponseUserRoleDTO;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;

import java.util.List;

public interface UserRoleService {
    public void initializeUserRoles();

    List<ResponseUserRoleDTO> findAllUserRole();

    CommonResponseDTO deleteUserRole(String userRoleId);

}
