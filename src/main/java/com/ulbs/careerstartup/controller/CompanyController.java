package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.CompanyApiDoc;
import com.ulbs.careerstartup.dto.CompanyDTO;
import com.ulbs.careerstartup.security.isOwnerRole.bibliography.IsBibliographyOwner;
import com.ulbs.careerstartup.security.isOwnerRole.company.IsCompanyOwner;
import com.ulbs.careerstartup.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/companies")
@Tag(name = "Company", description = "The Company API")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController implements CompanyApiDoc {

    private CompanyService companyService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public CompanyDTO findCompanyById(@PathVariable UUID id) {
        return companyService.findCompanyById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public Collection<CompanyDTO> findAllCompanies() {
        return companyService.findAllCompanies();
    }

    @GetMapping("{id}/icon")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public String getCompanyIcon(@PathVariable UUID id) {
        return companyService.getFaviconUrl(id);
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('COMPANY_REPRESENTATIVE', 'ADMIN')")
    public CompanyDTO saveCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.saveCompany(companyDTO);
    }

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('COMPANY_REPRESENTATIVE', 'ADMIN')")
    @IsBibliographyOwner
    @IsCompanyOwner
    public CompanyDTO updateCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.updateCompany(companyDTO);
    }

    @DeleteMapping("/{id}")
    @IsCompanyOwner
    @PreAuthorize("hasAnyAuthority('COMPANY_REPRESENTATIVE', 'ADMIN')")
    public void deleteCompany(@PathVariable UUID id) {
        companyService.deleteCompany(id);
    }
}
