package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.CompanyApiDoc;
import com.ulbs.careerstartup.dto.CompanyDTO;
import com.ulbs.careerstartup.service.CompanyService;
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
@RequestMapping("/companies")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Company", description = "The Company API")
public class CompanyController implements CompanyApiDoc {

    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<Collection<CompanyDTO>> findAllCompanies() {
        return ResponseEntity.ok(companyService.findAllCompanies());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(companyService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<CompanyDTO>> findCompaniesByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(companyService.findCompaniesByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> saveCompany(@RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyService.saveCompany(companyDTO));
    }

    @PatchMapping
    public ResponseEntity<CompanyDTO> updateCompany(@RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyService.updateCompany(companyDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCompany(@RequestBody CompanyDTO companyDTO) {
        companyService.deleteCompany(companyDTO);
        return ResponseEntity.noContent().build();
    }
}
