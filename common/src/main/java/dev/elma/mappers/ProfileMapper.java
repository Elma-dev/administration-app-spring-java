package dev.elma.mappers;


import dev.elma.dtos.ProfileDto;
import dev.elma.dtos.ProfileDtoRequest;
import dev.elma.entities.ProfileEntity;
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
