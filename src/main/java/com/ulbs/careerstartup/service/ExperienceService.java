package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.ExperienceDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.ExperienceRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ExperienceService {
    private ExperienceRepository experienceRepository;
    private Mapper mapper;

    public @NotNull Collection <ExperienceDTO> findAllExperiences() {
        return experienceRepository.findAll().stream().map(mapper::experienceToExperienceDTO).toList();
    }

    public ExperienceDTO findById(UUID id) {
        return experienceRepository.findById(id)
                .map(mapper::experienceToExperienceDTO)
                .orElseThrow(() -> new EntityNotFoundException("Event with id " + id + " not found"));
    }

    public Collection<ExperienceDTO> findExperiencesByCriteria(List<SearchCriteria> criteria) {
        return experienceRepository
                .findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10))
                .map(mapper::experienceToExperienceDTO)
                .toList();
    }

    public ExperienceDTO saveExperience(ExperienceDTO experienceDTO) {
        return mapper.experienceToExperienceDTO(experienceRepository.save(mapper.experienceDTOToExperience(experienceDTO)));
    }

    public ExperienceDTO updateExperience(ExperienceDTO experienceDTO) {
        return mapper.experienceToExperienceDTO(experienceRepository.save(mapper.experienceDTOToExperience(experienceDTO)));
    }

    public void deleteExperience(ExperienceDTO experienceDTO) {
        experienceRepository.delete(mapper.experienceDTOToExperience(experienceDTO));
    }
}
