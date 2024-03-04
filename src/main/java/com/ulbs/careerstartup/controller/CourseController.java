package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.CourseApiDoc;
import com.ulbs.careerstartup.dto.CourseDTO;
import com.ulbs.careerstartup.service.CourseService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Collection<CourseDTO>> findAllCourses() {
        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<CourseDTO>> findCoursesByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(courseService.findCoursesByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.saveCourse(courseDTO));
    }

    @PatchMapping
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.updateCourse(courseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@RequestBody CourseDTO courseDTO) {
        courseService.deleteCourse(courseDTO);
        return ResponseEntity.noContent().build();
    }
}
