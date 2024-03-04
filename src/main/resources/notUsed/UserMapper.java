/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.UserDTO;
import com.ulbs.careerstartup.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {EventMapper.class, BibliographyMapper.class, ExperienceMapper.class, LanguageMapper.class, NotificationMapper.class, ReferralMapper.class, SpecializationMapper.class, UserSkillsMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target = "createdEventsDTO", ignore = true)
    @Mapping(target = "bibliographiesDTO", source = "bibliographies")
    @Mapping(target = "experiencesDTO", ignore = true)
    @Mapping(target = "languagesDTO", source = "languages")
    @Mapping(target = "notificationsDTO", source = "notifications")
    @Mapping(target = "receivedReferralsDTO", source = "receivedReferrals")
    @Mapping(target = "specializationsDTO", source = "specializations")
    @Mapping(target = "skillsDTO", source = "skills")
    @Mapping(target = "jobHistoriesDTO", ignore = true)
    @Mapping(target = "jobCandidatesDTO", ignore = true)
    UserDTO userToUserDTO(User user);


    @Mapping(target = "events", ignore = true)
    @Mapping(target = "bibliographies", source = "bibliographiesDTO")
    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "languages", source = "languagesDTO")
    @Mapping(target = "notifications", source = "notificationsDTO")
    @Mapping(target = "receivedReferrals", source = "receivedReferralsDTO")
    @Mapping(target = "specializations", source = "specializationsDTO")
    @Mapping(target = "skills", source = "skillsDTO")
    @Mapping(target = "jobHistories", ignore = true)
    @Mapping(target = "jobCandidates", ignore = true)
    User userDTOToUser(UserDTO userDTO);
}
*/
