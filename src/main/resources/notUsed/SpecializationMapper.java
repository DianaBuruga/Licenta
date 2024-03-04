/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.SpecializationDTO;
import com.ulbs.careerstartup.entity.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, FacultyMapper.class, CourseMapper.class})
public interface SpecializationMapper {

    SpecializationMapper INSTANCE = Mappers.getMapper(SpecializationMapper.class);

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "faculty", target = "facultyDTO")
    @Mapping(source = "courses", target = "coursesDTO")
    SpecializationDTO specializationToSpecializationDTO(Specialization specialization);

    @Mapping(source = "userDTO", target = "user")
    @Mapping(source = "facultyDTO", target = "faculty")
    @Mapping(source = "coursesDTO", target = "courses")
    Specialization specializationDTOToSpecialization(SpecializationDTO specializationDTO);
}*/
