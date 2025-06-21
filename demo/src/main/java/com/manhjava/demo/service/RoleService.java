package com.manhjava.demo.service;

import com.manhjava.demo.dto.request.RoleRequest;
import com.manhjava.demo.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(RoleRequest request);

    List<RoleResponse> getAllRoles();

    void deleteRole(String roleName);
}
