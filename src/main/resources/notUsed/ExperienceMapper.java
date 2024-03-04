/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.ExperienceDTO;
import com.ulbs.careerstartup.entity.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ExperienceMapper {

    ExperienceMapper INSTANCE = Mappers.getMapper(ExperienceMapper.class);

    @Mapping(source = "user", target = "userDTO")
    ExperienceDTO experienceToExperienceDTO(Experience experience);

    @Mapping(source = "userDTO", target = "user")
    Experience experienceDTOToExperience(ExperienceDTO experienceDTO);
}*/
