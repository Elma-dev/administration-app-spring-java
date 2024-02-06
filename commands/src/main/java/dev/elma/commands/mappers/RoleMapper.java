package dev.elma.commands.mappers;

import dev.elma.commands.dtos.ProfileDto;
import dev.elma.commands.dtos.RoleDto;
import dev.elma.commands.entities.ProfileEntity;
import dev.elma.commands.entities.RoleEntity;
import org.modelmapper.ModelMapper;

public class RoleMapper {
    ModelMapper modelMapper = new ModelMapper();
    public RoleEntity toRoleEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, RoleEntity.class);
    }
    public RoleDto toRoleDto(RoleEntity roleEntity) {
        return modelMapper.map(roleEntity, RoleDto.class);
    }

}
