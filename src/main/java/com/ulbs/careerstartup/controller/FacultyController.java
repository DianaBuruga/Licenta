package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.FacultyApiDoc;
import com.ulbs.careerstartup.dto.FacultyDTO;
import com.ulbs.careerstartup.service.FacultyService;
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
@RequestMapping("/faculties")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Faculty", description = "The Faculty API")
public class FacultyController implements FacultyApiDoc {

    private FacultyService facultyService;

    @GetMapping
    public ResponseEntity<Collection<FacultyDTO>> findAllFaculties() {
        return ResponseEntity.ok(facultyService.findAllFaculties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(facultyService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<FacultyDTO>> findFacultiesByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(facultyService.findFacultiesByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<FacultyDTO> saveFaculty(@RequestBody FacultyDTO facultyDTO) {
        return ResponseEntity.ok(facultyService.saveFaculty(facultyDTO));
    }

    @PatchMapping
    public ResponseEntity<FacultyDTO> updateFaculty(@RequestBody FacultyDTO facultyDTO) {
        return ResponseEntity.ok(facultyService.updateFaculty(facultyDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFaculty(@RequestBody FacultyDTO facultyDTO) {
        facultyService.deleteFaculty(facultyDTO);
        return ResponseEntity.noContent().build();
    }
}
