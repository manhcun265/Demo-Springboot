package com.manhjava.demo.mapper;

import com.manhjava.demo.dto.request.UserCreateRequest;
import com.manhjava.demo.dto.request.UserUpdateRequest;
import com.manhjava.demo.dto.response.UserResponse;
import com.manhjava.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    
    User toEntity(UserCreateRequest request);

    @Mapping(target = "lastName", ignore = true)
    UserResponse toResponse(User user);
    
    void updateEntity(@MappingTarget User user, UserUpdateRequest request);
    
    // Thêm method để map list
    List<UserResponse> toResponseList(List<User> users);
}
