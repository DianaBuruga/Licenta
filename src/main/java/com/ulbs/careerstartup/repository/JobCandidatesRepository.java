package com.ulbs.careerstartup.repository;

import com.ulbs.careerstartup.entity.JobCandidates;
import com.ulbs.careerstartup.entity.pk.JobCandidatesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCandidatesRepository extends JpaRepository<JobCandidates, JobCandidatesPK>, JpaSpecificationExecutor<JobCandidates> {
}