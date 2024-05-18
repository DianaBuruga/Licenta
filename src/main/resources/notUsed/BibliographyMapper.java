package notUsed;

import com.ulbs.careerstartup.dto.BibliographyDTO;
import com.ulbs.careerstartup.entity.Bibliography;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", builder = @org.mapstruct.Builder(disableBuilder = true))
public interface BibliographyMapper {

    BibliographyMapper INSTANCE = Mappers.getMapper(BibliographyMapper.class);

    @InheritInverseConfiguration(name = "bibliographyDTOToBibliography")
    BibliographyDTO bibliographyToBibliographyDTO(Bibliography bibliography);

    @Mapping(target = "writer", ignore = true)
    @Mapping(target = "skill", ignore = true)
    Bibliography bibliographyDTOToBibliography(BibliographyDTO bibliographyDTO);
}
