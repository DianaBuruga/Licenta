package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.FacultyApiDoc;
import com.ulbs.careerstartup.dto.FacultyDTO;
import com.ulbs.careerstartup.service.FacultyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/faculties")
@Tag(name = "Faculty", description = "The Faculty API")
public class FacultyController implements FacultyApiDoc {

    private FacultyService facultyService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public Collection<FacultyDTO> findAllFaculties() {
        return facultyService.findAllFaculties();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public FacultyDTO findFacultyById(@PathVariable UUID id) {
        return facultyService.findFacultyById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('TEACHER','MODERATOR')")
    public FacultyDTO saveFaculty(@RequestBody FacultyDTO facultyDTO) {
        return facultyService.saveFaculty(facultyDTO);
    }

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('TEACHER','MODERATOR')")
    public FacultyDTO updateFaculty(@RequestBody FacultyDTO facultyDTO) {
        return facultyService.updateFaculty(facultyDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('TEACHER','MODERATOR')")
    public void deleteFaculty(@PathVariable UUID id) {
        facultyService.deleteFaculty(id);
    }
}
