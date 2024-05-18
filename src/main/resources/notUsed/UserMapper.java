/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.UserDTO;
import com.ulbs.careerstartup.entity.User;
import com.ulbs.careerstartup.exemplu.Car;
import org.mapstruct.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring", uses = {EventMapper.class, BibliographyMapper.class, ExperienceMapper.class, LanguageMapper.class, NotificationMapper.class, ReferralMapper.class, SpecializationMapper.class, UserSkillsMapper.class})
@Mapper(componentModel = "spring", uses = {LanguageMapper.class})
public interface UserMapper {

//    @AfterMapping
//    default void resolveDependencies(@MappingTarget User user) {
//        user.getLanguages().forEach(language -> language.setUser(user));
//    }

    @AfterMapping
    default void convertNameToUpperCase(@MappingTarget User carDto) {
        carDto.setName(carDto.getName().toUpperCase());
    }
    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "languages", source = "languagesDTO")
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "receivedReferrals", ignore = true)
    @Mapping(target = "specializations", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "jobHistories", ignore = true)
    User userDTOToUser(UserDTO userDTO);

    @InheritInverseConfiguration
    UserDTO userToUserDTO(User user);
}

// @Mapping(target = "jobCandidatesDTO", ignore = true)
*/
