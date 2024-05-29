package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.CourseApiDoc;
import com.ulbs.careerstartup.dto.CourseDTO;
import com.ulbs.careerstartup.service.CourseService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.BY_CRITERIA;

@RestController
@AllArgsConstructor
@RequestMapping("/courses")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Course", description = "The Course API")
public class CourseController implements CourseApiDoc {

    private CourseService courseService;

    @GetMapping
    public Collection<CourseDTO> findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDTO findCourseById(@PathVariable UUID id) {
        return courseService.findCourseById(id);
    }

    @PostMapping(BY_CRITERIA)
    public Collection<CourseDTO> findByCriteria(@RequestBody List<SearchCriteria> criteria) {
        return courseService.findByCriteria(criteria);
    }

    @PostMapping
    public CourseDTO saveCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.saveCourse(courseDTO);
    }

    @PatchMapping
    public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.updateCourse(courseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@RequestBody CourseDTO courseDTO) {
        courseService.deleteCourse(courseDTO);
    }
}
