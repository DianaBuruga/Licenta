package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.LanguageApiDoc;
import com.ulbs.careerstartup.dto.LanguageDTO;
import com.ulbs.careerstartup.service.LanguageService;
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
@RequestMapping("/languages")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Language", description = "The Language API")
public class LanguageController implements LanguageApiDoc {
    private LanguageService languageService;

    @GetMapping
    public ResponseEntity<Collection<LanguageDTO>> findAllLanguages() {
        return ResponseEntity.ok(languageService.findAllLanguages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(languageService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<LanguageDTO>> findLanguagesByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(languageService.findLanguagesByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<LanguageDTO> saveLanguage(@RequestBody LanguageDTO languageDTO) {
        return ResponseEntity.ok(languageService.saveLanguage(languageDTO));
    }

    @PatchMapping
    public ResponseEntity<LanguageDTO> updateLanguage(@RequestBody LanguageDTO languageDTO) {
        return ResponseEntity.ok(languageService.updateLanguage(languageDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLanguage(@RequestBody LanguageDTO languageDTO) {
        languageService.deleteLanguage(languageDTO);
        return ResponseEntity.noContent().build();
    }
}
