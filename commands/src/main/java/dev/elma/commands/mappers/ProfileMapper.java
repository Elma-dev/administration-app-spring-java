package dev.elma.commands.mappers;

import dev.elma.commands.dtos.ProfileDto;
import dev.elma.commands.entities.ProfileEntity;
import org.modelmapper.ModelMapper;

public class ProfileMapper {
    ModelMapper modelMapper = new ModelMapper();
    public ProfileEntity toProfileEntity(ProfileDto profileDto) {
        return modelMapper.map(profileDto, ProfileEntity.class);
    }
    public ProfileDto toProfileDto(ProfileEntity profileEntity) {
        return modelMapper.map(profileEntity, ProfileDto.class);
    }

}
