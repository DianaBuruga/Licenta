/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.FileDTO;
import com.ulbs.careerstartup.entity.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    @Mapping(source = "id.tableId", target = "tableId")
    @Mapping(source = "id.tableName", target = "tableName")
    FileDTO fileToFileDTO(File file);

    @Mapping(target = "id.tableId", source = "tableId")
    @Mapping(target = "id.tableName", source = "tableName")
    File fileDTOToFile(FileDTO fileDTO);
}*/
