package com.manhjava.demo.controller;

// Import các class DTO, service, annotation và exception cần thiết

import com.manhjava.demo.dto.request.ApiResponse;
import com.manhjava.demo.dto.request.AuthenticationRequest;
import com.manhjava.demo.dto.request.IntrospectRequest;
import com.manhjava.demo.dto.request.LogoutRequest;
import com.manhjava.demo.dto.response.AuthenticationResponse;
import com.manhjava.demo.dto.response.IntrospectResponse;
import com.manhjava.demo.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

// Đánh dấu đây là một REST controller
@RestController
// Định nghĩa route gốc cho controller này
@RequestMapping("/api/auth")
// Tự động tạo constructor với các trường final
@RequiredArgsConstructor
// Thiết lập mặc định access level cho các trường là private và final
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    // Service xử lý logic xác thực
    private final AuthenticationService authenticationService;

    // API endpoint để lấy token đăng nhập
    // @param request: thông tin đăng nhập (username, password)
    // @return: ApiResponse chứa AuthenticationResponse (token, v.v.)
    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    // API endpoint để kiểm tra tính hợp lệ của token (introspect)
    // @param request: thông tin token cần kiểm tra
    // @return: ApiResponse chứa IntrospectResponse (thông tin token)
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@Valid @RequestBody IntrospectRequest request)
            throws JOSEException, ParseException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@Valid @RequestBody LogoutRequest request)
            throws JOSEException, ParseException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }
}
