/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.SkillDTO;
import com.ulbs.careerstartup.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BibliographyMapper.class, UserSkillsMapper.class, CourseMapper.class})
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    @Mapping(source = "bibliographies", target = "bibliographiesDTO")
    @Mapping(source = "userSkills", target = "userSkillsDTO")
    @Mapping(source = "courses", target = "coursesDTO")
    SkillDTO skillToSkillDTO(Skill skill);

    @Mapping(target = "bibliographies", ignore = true)
    @Mapping(target = "userSkills", ignore = true)
    @Mapping(target = "courses", ignore = true)
    Skill skillDTOToSkill(SkillDTO skillDTO);
}*/
