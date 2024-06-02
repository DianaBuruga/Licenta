package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.FacultyDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.FacultyRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FacultyService {

    private FacultyRepository facultyRepository;

    private Mapper mapper;


    public @NotNull Collection<FacultyDTO> findAllFaculties() {
        return facultyRepository.findAll().stream().map(mapper::facultyToFacultyDTO).toList();
    }

    public FacultyDTO findFacultyById(UUID id) {
        return facultyRepository.findById(id)
                .map(mapper::facultyToFacultyDTO)
                .orElseThrow(() -> new EntityNotFoundException("Faculty with id " + id + " not found"));
    }

    public Collection<FacultyDTO> findByCriteria(List<SearchCriteria> searchCriteria) {
        return facultyRepository
                .findAll(new GenericSpecification<>(searchCriteria), PageRequest.of(0, 10))
                .map(mapper::facultyToFacultyDTO)
                .toList();
    }

    public FacultyDTO saveFaculty(FacultyDTO facultyDTO) {
        return mapper.facultyToFacultyDTO(facultyRepository.save(mapper.facultyDTOToFaculty(facultyDTO)));
    }

    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        if (facultyRepository.existsById(facultyDTO.getId()))
            return mapper.facultyToFacultyDTO(facultyRepository.save(mapper.facultyDTOToFaculty(facultyDTO)));
        else
            throw new EntityNotFoundException("Faculty with id " + facultyDTO.getId() + " not found");
    }

    @Transactional
    public void deleteFaculty(UUID id) {
        facultyRepository.deleteById(id);
    }
}
