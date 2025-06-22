package com.manhjava.demo.mapper;

import com.manhjava.demo.dto.request.UserCreateRequest;
import com.manhjava.demo.dto.request.UserUpdateRequest;
import com.manhjava.demo.dto.response.UserResponse;
import com.manhjava.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreateRequest request);


    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

}
