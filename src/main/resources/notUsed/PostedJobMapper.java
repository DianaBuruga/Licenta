/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.PostedJobDTO;
import com.ulbs.careerstartup.entity.PostedJob;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class, JobCandidatesMapper.class})
public interface PostedJobMapper {

    PostedJobMapper INSTANCE = Mappers.getMapper(PostedJobMapper.class);

    @Mapping(source = "company", target = "companyDTO")
    @Mapping(source = "jobCandidatesById", target = "jobCandidatesDTO")
    PostedJobDTO postedJobToPostedJobDTO(PostedJob postedJob);

    @Mapping(source = "companyDTO", target = "company")
    @Mapping(target = "jobCandidatesById", source = "jobCandidatesDTO")
    PostedJob postedJobDTOToPostedJob(PostedJobDTO postedJobDTO);
}*/
