package com.ulbs.careerstartup.util;

import com.lowagie.text.DocumentException;
import com.ulbs.careerstartup.api.model.ApplicantsEmailRequest;
import com.ulbs.careerstartup.dto.*;
import com.ulbs.careerstartup.entity.*;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.CompanyRepository;
import com.ulbs.careerstartup.repository.JobHistoryRepository;
import com.ulbs.careerstartup.service.EmailService;
import com.ulbs.careerstartup.service.SearchService;
import com.ulbs.careerstartup.service.UserService;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@AllArgsConstructor
public class RecruiterNotificator {
    private static final String APPLICANTS = "Applicants";
    private SearchService searchService;
    private UserService userService;
    private ZipManager zipManager;
    private EmailService emailService;
    private CVGenerator cvGenerator;
    private Mapper mapper;
    private JobHistoryRepository jobHistoryRepository;
    private CompanyRepository companyRepository;

    public void notify(PostedJob job) throws DocumentException, IOException {
        List<FileDTO> files = new ArrayList<>();
        Collection<UserDTO> candidates = userService.findByCriteria(List.of(new SearchCriteria("jobCandidates.postedJob.id", "=", job.getId())));
        for (UserDTO candidate : candidates) {
            files.add(generateCVForZip(candidate));
        }
        FileDTO file = zipManager.createZipFromFiles(files);
        UserDTO user = userService.findByCriteria(List.of(new SearchCriteria("postedJobs.id", "=", job.getId()))).stream().findFirst().orElseThrow(() -> new EntityNotFoundException("User not found"));
        emailService.sendApplicants(new ApplicantsEmailRequest(user.getEmail(), APPLICANTS, user.getName(), job.getPosition(), file));
    }

    public FileDTO generateCVForZip(UserDTO userDTO) throws DocumentException, IOException {
        Collection<ExperienceDTO> experiences = searchService.search("experiences", Map.of("user.id", userDTO.getId().toString()));
        Collection<SpecializationDTO> specializations = searchService.search("specializations", Map.of("user.id", userDTO.getId().toString()));
        Collection<UserSkillsDTO> userSkills = searchService.search("userSkills", Map.of("user.id", userDTO.getId().toString()));
        Collection<LanguageDTO> languages = searchService.search("languages", Map.of("user.id", userDTO.getId().toString()));
        Collection<JobHistory> jobHistories = jobHistoryRepository
                .findAll(new GenericSpecification<>(List.of(new SearchCriteria("user.id", "=", userDTO.getId()))))
                .stream()
                .toList();
        jobHistories.forEach(
                jobHistory -> jobHistory.setCompany(
                        companyRepository.findAll(new GenericSpecification<>(List.of(new SearchCriteria("jobHistories.id", "=", jobHistory.getId()))))
                                .stream()
                                .findFirst()
                                .orElseThrow(() -> new EntityNotFoundException("Company not found"))));
        Collection<JobHistoryDTO> jobHistoryDTOS = jobHistories
                .stream()
                .map(jobHistory -> {
                    jobHistory.getCompany().setReviews(List.of());
                    return mapper.jobHistoryToJobHistoryDTO(jobHistory);
                }).toList();

        userDTO.setExperiencesDTO(experiences);
        userDTO.setSpecializationsDTO(specializations);
        userDTO.setSkillsDTO(userSkills);
        userDTO.setLanguagesDTO(languages);
        userDTO.setJobHistoriesDTO(jobHistoryDTOS);

        return mapper.fileToFileDTO(cvGenerator.generateCV(userDTO));
    }
}
