/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.LanguageDTO;
import com.ulbs.careerstartup.entity.Language;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface LanguageMapper {

    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    @Mapping(source = "user", target = "userDTO")
    LanguageDTO languageToLanguageDTO(Language language);

    @Mapping(source = "userDTO", target = "user")
    Language languageDTOToLanguage(LanguageDTO languageDTO);
}*/
