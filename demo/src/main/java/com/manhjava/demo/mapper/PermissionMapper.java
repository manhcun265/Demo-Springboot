package com.manhjava.demo.mapper;

import com.manhjava.demo.dto.request.PermissionRequest;
import com.manhjava.demo.dto.response.PermissionResponse;
import com.manhjava.demo.entity.Permission;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
