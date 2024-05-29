package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.LanguageApiDoc;
import com.ulbs.careerstartup.dto.LanguageDTO;
import com.ulbs.careerstartup.service.LanguageService;
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
@RequestMapping("users/languages")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Language", description = "The Language API")
public class LanguageController implements LanguageApiDoc {
    private LanguageService languageService;

    @GetMapping
    public Collection<LanguageDTO> findAllLanguages() {
        return languageService.findAllLanguages();
    }

    @GetMapping("/{id}")
    public LanguageDTO findLanguageById(@PathVariable UUID id) {
        return languageService.findLanguageById(id);
    }

    @PostMapping(BY_CRITERIA)
    public Collection<LanguageDTO> findByCriteria(@RequestBody List<SearchCriteria> criteria) {
        return languageService.findByCriteria(criteria);
    }

    @PostMapping
    public LanguageDTO saveLanguage(@RequestBody LanguageDTO languageDTO) {
        return languageService.saveLanguage(languageDTO);
    }

    @PatchMapping
    public LanguageDTO updateLanguage(@RequestBody LanguageDTO languageDTO) {
        return languageService.updateLanguage(languageDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable UUID id) {
        languageService.deleteLanguage(id);
    }
}
