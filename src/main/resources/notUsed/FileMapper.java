/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.FileDTO;
import com.ulbs.careerstartup.entity.File;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(source = "id.tableId", target = "tableId")
    @Mapping(source = "id.tableName", target = "tableName")
    FileDTO fileToFileDTO(File file);

    @InheritInverseConfiguration(name = "fileToFileDTO")
    File fileDTOToFile(FileDTO fileDTO);

    default File multipartFileToFile(MultipartFile multipartFile) throws IOException {
        return File.builder()
                .name(StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename())))
                .content(multipartFile.getBytes())
                .build();
    }
}
*/
