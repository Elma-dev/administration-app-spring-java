package dev.elma.commands.mappers;

import dev.elma.commands.dtos.UserDto;
import dev.elma.commands.dtos.UserDtoRequest;
import dev.elma.commands.entities.UserEntity;
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
