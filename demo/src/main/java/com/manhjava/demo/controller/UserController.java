package com.manhjava.demo.controller;

// Import các class DTO, entity, service, annotation và logger cần thiết

import com.manhjava.demo.dto.request.ApiResponse;
import com.manhjava.demo.dto.request.UserCreateRequest;
import com.manhjava.demo.dto.request.UserUpdateRequest;
import com.manhjava.demo.dto.response.UserResponse;
import com.manhjava.demo.entity.User;
import com.manhjava.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Đánh dấu đây là một REST controller
@RestController
// Định nghĩa route gốc cho controller này
@RequestMapping("/api/users")
// Tự động tạo constructor với các trường final
@RequiredArgsConstructor
// Tích hợp logger cho class
@Slf4j
public class UserController {

    // Service xử lý logic liên quan đến User
    private final UserService userService;

    // API endpoint để tạo mới user
    // @param request: thông tin user cần tạo
    // @return: ApiResponse chứa User vừa tạo
    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request){
        log.info("Controller: create User");
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    // API endpoint lấy danh sách tất cả user
    // @return: ApiResponse chứa danh sách UserResponse
    @GetMapping
    ApiResponse<List<UserResponse>> findAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority ->
                log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.findAll())
                .build();
    }

    // API endpoint lấy thông tin user theo id
    // @param id: id của user
    // @return: ApiResponse chứa UserResponse
    @GetMapping("/{id}")
    ApiResponse<UserResponse> findUserById(@PathVariable Long id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.findById(id))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    // API endpoint cập nhật thông tin user theo id
    // @param id: id của user
    // @param request: thông tin cập nhật
    // @return: ApiResponse chứa UserResponse đã cập nhật
    @PutMapping("/{id}")
    ApiResponse<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
        log.info("Updating user with id: {}", id);
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(id, request));
        return apiResponse;
    }

    // API endpoint xóa user theo id
    // @param id: id của user cần xóa
    // @return: ApiResponse rỗng nếu xóa thành công
    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        return new ApiResponse<>();
    }
}
