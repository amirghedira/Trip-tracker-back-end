package com.miecolo.authservice.mapper;

import com.miecolo.authservice.dto.UserRegisterDTO;
import com.miecolo.authservice.dto.UserUpdateDTO;
import com.miecolo.authservice.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserUpdateMapper {
    UserUpdateDTO fromUser(User user);
    User toUser(UserUpdateDTO userUpdateDTO);

}
