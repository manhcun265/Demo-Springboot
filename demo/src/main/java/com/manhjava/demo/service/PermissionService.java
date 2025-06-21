package com.manhjava.demo.service;

import com.manhjava.demo.dto.request.PermissionRequest;
import com.manhjava.demo.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);

    List<PermissionResponse> getAllPermissions();

    void deletePermission(String permission);
}
