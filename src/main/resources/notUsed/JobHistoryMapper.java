/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.JobHistoryDTO;
import com.ulbs.careerstartup.entity.JobHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CompanyMapper.class})
public interface JobHistoryMapper {

    JobHistoryMapper INSTANCE = Mappers.getMapper(JobHistoryMapper.class);

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "company", target = "companyDTO")
    JobHistoryDTO jobHistoryToJobHistoryDTO(JobHistory jobHistory);

    @Mapping(source = "userDTO", target = "user")
    @Mapping(source = "companyDTO", target = "company")
    JobHistory jobHistoryDTOToJobHistory(JobHistoryDTO jobHistoryDTO);
}*/
