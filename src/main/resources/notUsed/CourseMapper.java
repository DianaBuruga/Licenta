/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.CourseDTO;
import com.ulbs.careerstartup.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {SpecializationMapper.class, SkillMapper.class})
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "specialization", target = "specializationDTO")
    @Mapping(source = "skills", target = "skillsDTO")
    CourseDTO courseToCourseDTO(Course course);

    @Mapping(target = "specialization", source = "specializationDTO")
    @Mapping(target = "skills", source = "skillsDTO")
    Course courseDTOToCourse(CourseDTO courseDTO);
}
*/
