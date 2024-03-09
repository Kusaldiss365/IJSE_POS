package com.pos.posapi.util.mapper;

import com.pos.posapi.dto.UserRoleDTO;
import com.pos.posapi.dto.responsedto.ResponseUserRoleDTO;
import com.pos.posapi.enity.UserRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleDTO toUserRoleDto(UserRole userRole);

    List<ResponseUserRoleDTO> toUserRoleList(List<UserRole> all);
}
