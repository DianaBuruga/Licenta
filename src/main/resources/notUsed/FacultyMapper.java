/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.FacultyDTO;
import com.ulbs.careerstartup.entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {SpecializationMapper.class})
public interface FacultyMapper {

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

    @Mapping(source = "specializations", target = "specializationsDTO")
    FacultyDTO facultyToFacultyDTO(Faculty faculty);

    @Mapping(source = "specializationsDTO", target = "specializations")
    Faculty facultyDTOToFaculty(FacultyDTO facultyDTO);
}*/
