package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.CourseDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.CourseRepository;
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
public class CourseService {
    private CourseRepository courseRepository;
    private Mapper mapper;

    public @NotNull Collection<CourseDTO> findAllCourses() {
        return courseRepository.findAll().stream().map(mapper::courseToCourseDTO).toList();
    }

    public CourseDTO findById(UUID id) {
        return courseRepository.findById(id)
                .map(mapper::courseToCourseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + id + " not found"));
    }

    public Collection<CourseDTO> findCoursesByCriteria(List<SearchCriteria> searchCriteria) {
        return courseRepository
                .findAll(new GenericSpecification<>(searchCriteria), PageRequest.of(0, 10))
                .map(mapper::courseToCourseDTO)
                .toList();
    }

    public CourseDTO saveCourse(CourseDTO courseDTO) {
        return mapper.courseToCourseDTO(courseRepository.save(mapper.courseDTOToCourse(courseDTO)));
    }

    public CourseDTO updateCourse(CourseDTO courseDTO) {
        if (courseRepository.existsById(courseDTO.getId()))
            return mapper.courseToCourseDTO(courseRepository.save(mapper.courseDTOToCourse(courseDTO)));
        else
            throw new EntityNotFoundException("Course with id " + courseDTO.getId() + " not found");
    }

    @Transactional
    public void deleteCourse(CourseDTO courseDTO) {
        courseRepository.delete(mapper.courseDTOToCourse(courseDTO));
    }
}
