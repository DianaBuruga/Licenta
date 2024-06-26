package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.CourseDTO;
import com.ulbs.careerstartup.entity.Course;
import com.ulbs.careerstartup.entity.Skill;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.CourseRepository;
import com.ulbs.careerstartup.repository.SkillRepository;
import com.ulbs.careerstartup.repository.SpecializationRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.sun.activation.registries.LogSupport.log;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {
    private CourseRepository courseRepository;
    private Mapper mapper;

    private SpecializationRepository specializationRepository;
    private SkillRepository skillRepository;

    public @NotNull Collection<CourseDTO> findAllCourses() {
        return courseRepository.findAll().stream().map(mapper::courseToCourseDTO).toList();
    }

    public CourseDTO findCourseById(UUID id) {
        return courseRepository.findById(id)
                .map(mapper::courseToCourseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + id + " not found"));
    }

    public Collection<CourseDTO> findByCriteria(List<SearchCriteria> searchCriteria) {
        return courseRepository
                .findAll(new GenericSpecification<>(searchCriteria))
                .stream()
                .map(mapper::courseToCourseDTO)
                .toList();
    }

    public CourseDTO saveCourse(CourseDTO courseDTO) {
        log("saveCourse");
        Course course = mapper.courseDTOToCourse(courseDTO);
        course.setSpecialization(
                specializationRepository.findById(courseDTO.getSpecializationDTO().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Specialization with id " + courseDTO.getSpecializationDTO().getId() + " not found")));
        course.setSkills(courseDTO.getSkillsDTO()
                .stream()
                .map(mapper::skillDTOToSkill)
                .map(this::processSkill)
                .collect(Collectors.toList()));
        return mapper.courseToCourseDTO(courseRepository.save(course));
    }

    public CourseDTO updateCourse(CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Course with id " + courseDTO.getId() + " not found"));
        if (courseDTO.getSpecializationDTO() != null) {
            course.setSpecialization(specializationRepository.findById(courseDTO.getSpecializationDTO().getId()).orElseThrow(() -> new EntityNotFoundException("Specialization with id " + courseDTO.getSpecializationDTO().getId() + " not found")));
        }
        if (courseDTO.getSkillsDTO() != null) {
            course.setSkills(courseDTO.getSkillsDTO()
                    .stream()
                    .map(mapper::skillDTOToSkill)
                    .map(this::processSkill)
                    .collect(Collectors.toList()));
        }
        if (courseDTO.getName() != null) {
            course.setName(courseDTO.getName());
        }
        if (courseDTO.getYear() != null) {
            course.setYear(courseDTO.getYear());
        }
        if (courseDTO.getSemester() != null) {
            course.setSemester(courseDTO.getSemester());
        }
        return mapper.courseToCourseDTO(courseRepository.save(course));
    }

    @Transactional
    public void deleteCourse(UUID id) {
        courseRepository.deleteById(id);
    }

    private Skill processSkill(Skill skill) {
        if (skill.getId() == null) {
            return skillRepository.save(Skill.builder().name(skill.getName()).build());
        } else {
            return skillRepository.findById(skill.getId())
                    .map(existingSkill ->  skillRepository.save(existingSkill))
                    .orElseGet(() -> skillRepository.save(Skill.builder().name(skill.getName()).build()));
        }
    }

    public boolean isCourseOwner(UUID courseId, Principal principal) {
        CourseDTO courseDTO = findByCriteria(List.of(new SearchCriteria("id", "=", courseId),
                new SearchCriteria("specialization.user.email", "=", principal.getName()))).stream().toList().get(0);
        return courseDTO != null;
    }
}
