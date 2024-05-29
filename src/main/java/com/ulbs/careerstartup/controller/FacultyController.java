package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.FacultyApiDoc;
import com.ulbs.careerstartup.dto.FacultyDTO;
import com.ulbs.careerstartup.service.FacultyService;
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
@RequestMapping("/faculties")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Faculty", description = "The Faculty API")
public class FacultyController implements FacultyApiDoc {

    private FacultyService facultyService;

    @GetMapping
    public Collection<FacultyDTO> findAllFaculties() {
        return facultyService.findAllFaculties();
    }

    @GetMapping("/{id}")
    public FacultyDTO findFacultyById(@PathVariable UUID id) {
        return facultyService.findFacultyById(id);
    }

    @PostMapping(BY_CRITERIA)
    public Collection<FacultyDTO> findByCriteria(@RequestBody List<SearchCriteria> criteria) {
        return facultyService.findByCriteria(criteria);
    }

    @PostMapping
    public FacultyDTO saveFaculty(@RequestBody FacultyDTO facultyDTO) {
        return facultyService.saveFaculty(facultyDTO);
    }

    @PatchMapping
    public FacultyDTO updateFaculty(@RequestBody FacultyDTO facultyDTO) {
        return facultyService.updateFaculty(facultyDTO);
    }

    @DeleteMapping
    public void deleteFaculty(@RequestBody FacultyDTO facultyDTO) {
        facultyService.deleteFaculty(facultyDTO);
    }
}
