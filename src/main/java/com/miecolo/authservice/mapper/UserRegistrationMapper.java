package com.miecolo.authservice.mapper;

import com.miecolo.authservice.dto.UserRegisterDTO;
import com.miecolo.authservice.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {
    UserRegisterDTO fromUser(User user);

    User toUser(UserRegisterDTO userRegisterDTO);

}
