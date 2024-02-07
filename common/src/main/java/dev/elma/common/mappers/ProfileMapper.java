package dev.elma.common.mappers;


import dev.elma.common.dtos.ProfileDto;
import dev.elma.common.dtos.ProfileDtoRequest;
import dev.elma.common.entities.ProfileEntity;
import org.modelmapper.ModelMapper;

public class ProfileMapper {
    ModelMapper modelMapper = new ModelMapper();
    public ProfileEntity toProfileEntity(ProfileDto profileDto) {
        return modelMapper.map(profileDto, ProfileEntity.class);
    }
    public ProfileEntity toProfileEntity(ProfileDtoRequest profileDtoRequest) {
        return modelMapper.map(profileDtoRequest, ProfileEntity.class);
    }
    public ProfileDto toProfileDto(ProfileEntity profileEntity) {
        return modelMapper.map(profileEntity, ProfileDto.class);
    }

}
