package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.SpecializationDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.SpecializationRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class SpecializationService {
    private SpecializationRepository specializationRepository;
    private Mapper mapper;

    public Collection<SpecializationDTO> findAllSpecializations() {
        return specializationRepository
                .findAll()
                .stream()
                .map(mapper::specializationToSpecializationDTO)
                .toList();
    }

    public SpecializationDTO findById(UUID id) {
        return specializationRepository.findById(id)
                .map(mapper::specializationToSpecializationDTO)
                .orElseThrow(() -> new EntityNotFoundException("Specialization with id " + id + " not found"));
    }

    public Collection<SpecializationDTO> findSpecializationsByCriteria(List<SearchCriteria> criteria) {
        return specializationRepository
                .findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10))
                .map(mapper::specializationToSpecializationDTO)
                .toList();
    }

    public SpecializationDTO saveSpecialization(SpecializationDTO specializationDTO) {
        return mapper.specializationToSpecializationDTO(specializationRepository.save(mapper.specializationDTOToSpecialization(specializationDTO)));
    }

    public SpecializationDTO updateSpecialization(SpecializationDTO specializationDTO) {
        return mapper.specializationToSpecializationDTO(specializationRepository.save(mapper.specializationDTOToSpecialization(specializationDTO)));
    }

    public void deleteSpecialization(SpecializationDTO specializationDTO) {
        specializationRepository.delete(mapper.specializationDTOToSpecialization(specializationDTO));
    }
}
