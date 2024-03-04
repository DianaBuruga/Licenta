/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.CompanyDTO;
import com.ulbs.careerstartup.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {JobHistoryMapper.class, PostedJobMapper.class, ReviewMapper.class})
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(target = "jobHistoriesDTO", source = "jobHistories")
    @Mapping(target = "postedJobsDTO", source = "postedJobs")
    @Mapping(target = "reviewsDTO", source = "reviews")
    CompanyDTO companyToCompanyDTO(Company company);

    @Mapping(target = "jobHistories", source = "jobHistoriesDTO")
    @Mapping(target = "postedJobs", source = "postedJobsDTO")
    @Mapping(target = "reviews", source = "reviewsDTO")
    Company companyDTOToCompany(CompanyDTO companyDTO);
}
*/
