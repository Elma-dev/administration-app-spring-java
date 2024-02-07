package dev.elma.mappers;

import dev.elma.dtos.UserDto;
import dev.elma.dtos.UserDtoRequest;
import dev.elma.entities.UserEntity;
import org.modelmapper.ModelMapper;

public class UserMapper {
    ModelMapper modelMapper = new ModelMapper();
    public UserEntity toUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
    public UserDto toUserDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }
    public UserEntity toUserEntity(UserDtoRequest userDtoRequest) {
        return modelMapper.map(userDtoRequest, UserEntity.class);
    }
}
