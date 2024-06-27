package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.constant.JobStatus;
import com.ulbs.careerstartup.dto.PostedJobDTO;
import com.ulbs.careerstartup.entity.PostedJob;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.CompanyRepository;
import com.ulbs.careerstartup.repository.PostedJobRepository;
import com.ulbs.careerstartup.repository.SkillRepository;
import com.ulbs.careerstartup.repository.UserRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ulbs.careerstartup.constant.Constants.NOT_FOUND;

@Service
@AllArgsConstructor
public class PostedJobService {

    private PostedJobRepository postedJobRepository;

    private CompanyRepository companyRepository;

    private SkillRepository skillRepository;

    private UserRepository userRepository;

    private Mapper mapper;

    public Collection<PostedJobDTO> findAllPostedJobs() {
        return postedJobRepository.findAll().stream().map(mapper::postedJobToPostedJobDTO).toList();
    }

    public PostedJobDTO findPostedJobById(UUID id) {
        return postedJobRepository.findById(id).map(mapper::postedJobToPostedJobDTO).orElseThrow(() -> new EntityNotFoundException("PostedJob with id " + id + NOT_FOUND));
    }

    public Collection<PostedJobDTO> findByCriteria(List<SearchCriteria> searchCriteria) {
        return postedJobRepository
                .findAll(new GenericSpecification<>(searchCriteria))
                .stream()
                .map(mapper::postedJobToPostedJobDTO).toList();
    }

    public PostedJobDTO savePostedJob(PostedJobDTO postedJobDTO, Principal principal) {
        if (postedJobDTO.getId() != null) {
            throw new EntityNotFoundException("PostedJob with id " + postedJobDTO.getId() + " already exists");
        }
        PostedJob postedJob = mapper.postedJobDTOToPostedJob(postedJobDTO);
        postedJob.setPostedDate(Timestamp.from(Instant.now()));
        setJobStatus(postedJob);
        setJobSkills(postedJobDTO, postedJob);
        setUser(postedJob, principal);
        setCompany(postedJob, postedJobDTO);
        return mapper.postedJobToPostedJobDTO(postedJobRepository.save(postedJob));
    }

    public PostedJobDTO updatePostedJob(PostedJobDTO postedJobDTO) {
        PostedJob postedJob = postedJobRepository
                .findById(postedJobDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("PostedJob with id " + postedJobDTO.getId() + NOT_FOUND));
        postedJob.setDescription(postedJobDTO.getDescription());
        postedJob.setPosition(postedJobDTO.getPosition());
        postedJob.setLocation(postedJobDTO.getLocation());
        postedJob.setType(postedJobDTO.getType());
        postedJob.setOpenUntil(mapper.mapStringToTimestamp(postedJobDTO.getOpenUntil()));
        setJobSkills(postedJobDTO, postedJob);
        setJobStatus(postedJob);
        return mapper.postedJobToPostedJobDTO(postedJobRepository.save(postedJob));
    }

    @Transactional
    public void deletePostedJob(UUID id) {
        postedJobRepository.deleteById(id);
    }

    private void setJobStatus(PostedJob postedJob) {
        if (postedJob.getOpenUntil().toInstant().isAfter(Instant.now())) {
            postedJob.setStatus(JobStatus.ACTIVE);
        } else {
            postedJob.setStatus(JobStatus.INACTIVE);
        }
    }

    private void setJobSkills(PostedJobDTO postedJobDTO, PostedJob postedJob) {
        if (postedJobDTO.getSkillsDTO() != null) {
            postedJob.setSkills(postedJobDTO
                    .getSkillsDTO()
                    .stream()
                    .map(skill -> skillRepository.findById(skill.getId())
                            .orElseGet(() -> skillRepository.save(mapper.skillDTOToSkill(skill))))
                    .collect(Collectors.toList()));
        }
    }

    private void setCompany(PostedJob postedJob, PostedJobDTO postedJobDTO) {
        postedJob.setCompany(
                postedJob.getUser().getJobHistories().stream().findFirst()
                        .map(jobHistory -> jobHistory.getCompany())
                        .orElseThrow(() -> new EntityNotFoundException("Company with id " + postedJobDTO.getCompanyDTO().getId() + " not found"))
        );
    }

    private void setUser(PostedJob postedJob, Principal principal) {
        postedJob.setUser(userRepository
                .findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User with email" + principal.getName() + NOT_FOUND)));
    }

    public boolean isOwner(UUID id, Principal principal) {
        PostedJobDTO postedJobDTO = findByCriteria(List.of(new SearchCriteria("id", "=", id),
                new SearchCriteria("user.email", "=", principal.getName()))).stream().toList().get(0);
        return postedJobDTO != null;
    }
}
