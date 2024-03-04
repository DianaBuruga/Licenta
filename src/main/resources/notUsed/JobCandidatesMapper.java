/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.JobCandidatesDTO;
import com.ulbs.careerstartup.entity.JobCandidates;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, PostedJobMapper.class})
public interface JobCandidatesMapper {

    JobCandidatesMapper INSTANCE = Mappers.getMapper(JobCandidatesMapper.class);

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "postedJob", target = "postedJobDTO")
    @Mapping(source = "id.candidateId", target = "candidateId")
    @Mapping(source = "id.jobId", target = "jobId")
    JobCandidatesDTO jobCandidatesToJobCandidatesDTO(JobCandidates jobCandidates);

    @Mapping(target = "user", source = "userDTO")
    @Mapping(target = "postedJob", source = "postedJobDTO")
    @Mapping(target = "id.candidateId", source = "candidateId")
    @Mapping(target = "id.jobId", source = "jobId")
    JobCandidates jobCandidatesDTOToJobCandidates(JobCandidatesDTO jobCandidatesDTO);
}*/
