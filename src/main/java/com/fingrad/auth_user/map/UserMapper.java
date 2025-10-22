package com.fingrad.auth_user.map;

import com.fingrad.auth_user.dto.request.RegistrationRequest;
import com.fingrad.auth_user.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  RegistrationRequest toRegistrationRequestDto(User entity);

}