package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.BibliographyDTO;
import com.ulbs.careerstartup.entity.Bibliography;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BibliographyMapper {
    BibliographyMapper INSTANCE = Mappers.getMapper(BibliographyMapper.class);

    BibliographyDTO bibliographyToBibliographyDTO(Bibliography bibliography);

    Bibliography bibliographyDTOToBibliography(BibliographyDTO bibliographyDTO);
}
