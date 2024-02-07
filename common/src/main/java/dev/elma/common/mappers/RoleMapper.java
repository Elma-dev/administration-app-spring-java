package dev.elma.common.mappers;

import dev.elma.common.dtos.RoleDto;
import dev.elma.common.entities.RoleEntity;
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
