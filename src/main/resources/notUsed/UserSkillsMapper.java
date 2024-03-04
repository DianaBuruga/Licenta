/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.UserSkillsDTO;
import com.ulbs.careerstartup.entity.UserSkills;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, SkillMapper.class})
public interface UserSkillsMapper {

    UserSkillsMapper INSTANCE = Mappers.getMapper(UserSkillsMapper.class);

    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.skillId", target = "skillId")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "skill", target = "skillDTO")
    @Mapping(source = "proficiency", target = "proficiencyDTO")
    UserSkillsDTO userSkillsToUserSkillsDTO(UserSkills userSkills);

    @Mapping(target = "id.userId", source = "userId")
    @Mapping(target = "id.skillId", source = "skillId")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "skill", source = "skillDTO")
    @Mapping(target = "proficiency", source = "proficiencyDTO")
    UserSkills userSkillsDTOToUserSkills(UserSkillsDTO userSkillsDTO);
}*/
