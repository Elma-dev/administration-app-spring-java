package dev.elma.mappers;

import dev.elma.dtos.RoleDto;
import dev.elma.entities.RoleEntity;
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
