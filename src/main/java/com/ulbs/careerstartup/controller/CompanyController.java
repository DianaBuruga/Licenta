package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.CompanyApiDoc;
import com.ulbs.careerstartup.dto.CompanyDTO;
import com.ulbs.careerstartup.service.CompanyService;
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
@RequestMapping("/companies")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Company", description = "The Company API")
public class CompanyController implements CompanyApiDoc {

    private CompanyService companyService;

    @GetMapping("/{id}")
    public CompanyDTO findCompanyById(@PathVariable UUID id) {
        return companyService.findCompanyById(id);
    }

    @GetMapping
    public Collection<CompanyDTO> findAllCompanies() {
        return companyService.findAllCompanies();
    }

    @GetMapping(BY_CRITERIA)
    public Collection<CompanyDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return companyService.findCompaniesByCriteria(criteria);
    }

    @PostMapping
    public CompanyDTO saveCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.saveCompany(companyDTO);
    }

    @PatchMapping
    public CompanyDTO updateCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.updateCompany(companyDTO);
    }

    @DeleteMapping
    public void deleteCompany(@RequestBody CompanyDTO companyDTO) {
        companyService.deleteCompany(companyDTO);
    }
}
