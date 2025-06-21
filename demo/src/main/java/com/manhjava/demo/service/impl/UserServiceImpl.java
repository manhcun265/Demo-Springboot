package com.manhjava.demo.service.impl;

import com.manhjava.demo.dto.request.UserCreateRequest;
import com.manhjava.demo.dto.request.UserUpdateRequest;
import com.manhjava.demo.dto.response.UserResponse;
import com.manhjava.demo.entity.User;
import com.manhjava.demo.enums.Role;
import com.manhjava.demo.exception.AppException;
import com.manhjava.demo.exception.ErrorCode;
import com.manhjava.demo.mapper.UserMapper;
import com.manhjava.demo.repository.UserRepository;
import com.manhjava.demo.service.UserService;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

// Triển khai các phương thức thao tác với user (tạo, cập nhật, xóa, tìm kiếm)
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    // Repository thao tác với dữ liệu user
    private final UserRepository userRepository;
    // Mapper chuyển đổi giữa entity và DTO
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    // Tạo mới user
    @Override
    public User createUser(UserCreateRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXITSTED);
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITSTED));

        return userMapper.toUserResponse(user);
    }

    // Cập nhật thông tin user
    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITSTED));

        userMapper.updateEntity(user, request);
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(savedUser);
    }

    // Xóa user theo id
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITSTED));
        userRepository.delete(user);
    }


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> findAll() {
        log.info("In method getAllUsers");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    // Lấy thông tin user theo id
    @Override
    @PostAuthorize("hasRole('ADMIN')")
    public UserResponse findById(Long id) {
        log.info("In method findById with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITSTED));
        return userMapper.toUserResponse(user);
    }
}
