package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.CourseApiDoc;
import com.ulbs.careerstartup.dto.CourseDTO;
import com.ulbs.careerstartup.security.isOwnerRole.company.IsCompanyOwner;
import com.ulbs.careerstartup.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/courses")
@Tag(name = "Course", description = "The Course API")
public class CourseController implements CourseApiDoc {

    private CourseService courseService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public Collection<CourseDTO> findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public CourseDTO findCourseById(@PathVariable UUID id) {
        return courseService.findCourseById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public CourseDTO saveCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.saveCourse(courseDTO);
    }

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    @IsCompanyOwner
    public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.updateCourse(courseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    @IsCompanyOwner
    public void deleteCourse(@PathVariable UUID id) {
        courseService.deleteCourse(id);
    }
}
