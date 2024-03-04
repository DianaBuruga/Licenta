//package com.ulbs.careerstartup.mapper;
//
//import com.ulbs.careerstartup.dto.BibliographyDTO;
//import com.ulbs.careerstartup.entity.Bibliography;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(componentModel = "spring")
//public interface BibliographyMapper {
//
//    BibliographyMapper INSTANCE = Mappers.getMapper(BibliographyMapper.class);
//    @Mapping(source = "writer", target = "writerDTO")
//    @Mapping(source = "skill", target = "skillDTO")
//    BibliographyDTO bibliographyToBibliographyDTO(Bibliography bibliography);
//
//    @Mapping(source = "writerDTO", target = "writer")
//    @Mapping(source = "skillDTO", target = "skill")
//    Bibliography bibliographyDTOToBibliography(BibliographyDTO bibliographyDTO);
//}
