package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.ExperienceDTO;
import com.ulbs.careerstartup.entity.Experience;
import com.ulbs.careerstartup.entity.User;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.ExperienceRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotNull;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ExperienceService {
    private ExperienceRepository experienceRepository;
    private Mapper mapper;

    public @NotNull Collection<ExperienceDTO> findAllExperiences() {
        return experienceRepository.findAll().stream().map(mapper::experienceToExperienceDTO).toList();
    }

    public ExperienceDTO findExperienceById(UUID id) {
        return experienceRepository.findById(id)
                .map(mapper::experienceToExperienceDTO)
                .orElseThrow(() -> new EntityNotFoundException("Event with id " + id + " not found"));
    }

    public Collection<ExperienceDTO> findByCriteria(List<SearchCriteria> criteria) {
        return experienceRepository
                .findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10))
                .map(mapper::experienceToExperienceDTO)
                .toList();
    }

    public ExperienceDTO saveExperience(ExperienceDTO experienceDTO) {
        User user = mapper.userDTOToUser(experienceDTO.getUserDTO());
        Experience experience = mapper.experienceDTOToExperience(experienceDTO);
        experience.setUser(user);
        return mapper.experienceToExperienceDTO(experienceRepository.save(experience));
    }

    public ExperienceDTO updateExperience(ExperienceDTO experienceDTO) {
        User user = mapper.userDTOToUser(experienceDTO.getUserDTO());
        Experience experience = mapper.experienceDTOToExperience(experienceDTO);
        experience.setUser(user);
        return mapper.experienceToExperienceDTO(experienceRepository.save(experience));
    }

    public void deleteExperience(UUID id) {
        experienceRepository.deleteById(id);
    }

    public boolean isOwner(UUID id, Principal principal) {
        ExperienceDTO experienceDTO = findByCriteria(List.of(new SearchCriteria("id", "=", id),
                new SearchCriteria("user.email", "=", principal.getName()))).stream().toList().get(0);
        return experienceDTO != null;
    }
}
