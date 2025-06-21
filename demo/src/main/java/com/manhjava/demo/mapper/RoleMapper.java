package com.manhjava.demo.mapper;

import com.manhjava.demo.dto.request.RoleRequest;
import com.manhjava.demo.dto.response.RoleResponse;
import com.manhjava.demo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

}
