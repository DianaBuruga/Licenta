package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.SpecializationDTO;
import com.ulbs.careerstartup.entity.Faculty;
import com.ulbs.careerstartup.entity.Specialization;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.FacultyRepository;
import com.ulbs.careerstartup.repository.SpecializationRepository;
import com.ulbs.careerstartup.repository.UserRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class SpecializationService {
    private SpecializationRepository specializationRepository;
    private FacultyRepository facultyRepository;

    private UserRepository userRepository;
    private Mapper mapper;

    public Collection<SpecializationDTO> findAllSpecializations() {
        return specializationRepository
                .findAll()
                .stream()
                .map(mapper::specializationToSpecializationDTO)
                .toList();
    }

    public SpecializationDTO findSpecializationById(UUID id) {
        return specializationRepository.findById(id)
                .map(mapper::specializationToSpecializationDTO)
                .orElseThrow(() -> new EntityNotFoundException("Specialization with id " + id + " not found"));
    }

    public Collection<SpecializationDTO> findByCriteria(List<SearchCriteria> criteria) {
        return specializationRepository
                .findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10))
                .map(mapper::specializationToSpecializationDTO)
                .toList();
    }

    public SpecializationDTO saveSpecialization(SpecializationDTO specializationDTO) {
        Specialization specialization = mapper.specializationDTOToSpecialization(specializationDTO);
        String facultyName = specializationDTO.getFacultyDTO().getName();
        facultyRepository.findByName(facultyName)
                .ifPresentOrElse(
                        specialization::setFaculty,
                        () -> specialization.setFaculty(facultyRepository.save(Faculty.builder().name(facultyName).build())));
        userRepository.findById(specializationDTO.getUserDTO().getId())
                .ifPresentOrElse(
                        specialization::setUser,
                        () -> {
                            throw new EntityNotFoundException("User with id " + specializationDTO.getUserDTO().getId() + " not found");
                        });
        return mapper.specializationToSpecializationDTO(specializationRepository.save(specialization));
    }

    public SpecializationDTO updateSpecialization(SpecializationDTO specializationDTO) {
        Specialization specialization = specializationRepository.findById(specializationDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Specialization with id " + specializationDTO.getId() + " not found"));
        if (specializationDTO.getFacultyDTO() != null) {
            String facultyName = specializationDTO.getFacultyDTO().getName();
            facultyRepository.findByName(facultyName)
                    .ifPresentOrElse(
                            specialization::setFaculty,
                            () -> specialization.setFaculty(facultyRepository.save(Faculty.builder().name(facultyName).build())));
        }
        if (specializationDTO.getStartedDate() != null) {
            specialization.setStartedDate(mapper.mapStringToTimestamp(specializationDTO.getStartedDate()));
        }
        if (specializationDTO.getFinishedDate() != null) {
            specialization.setFinishedDate(mapper.mapStringToTimestamp(specializationDTO.getFinishedDate()));
        }
        if (specializationDTO.getDegree() != null) {
            specialization.setDegree(specializationDTO.getDegree());
        }
        if (specializationDTO.getName() != null) {
            specialization.setName(specializationDTO.getName());
        }
        return mapper.specializationToSpecializationDTO(specializationRepository.save(specialization));
    }

    public void deleteSpecialization(UUID id) {
        specializationRepository.deleteById(id);
    }

    public boolean isSpecializationOwner(UUID id, Principal principal) {
        return findByCriteria(List.of(new SearchCriteria("id", "=", id),
                new SearchCriteria("user.email","=",principal.getName())))
                .stream().toList().get(0) != null;
    }
}
