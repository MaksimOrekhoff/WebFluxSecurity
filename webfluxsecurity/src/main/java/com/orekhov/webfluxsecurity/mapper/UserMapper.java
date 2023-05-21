package com.orekhov.webfluxsecurity.mapper;

import com.orekhov.webfluxsecurity.dto.UserDto;
import com.orekhov.webfluxsecurity.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapToUserDto(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity mapToUserEntity(UserDto userDto);
}
