package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.LanguageDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.LanguageRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class LanguageService {
    private LanguageRepository languageRepository;
    private Mapper mapper;

    public Collection<LanguageDTO> findAllLanguages() {
        return languageRepository
                .findAll()
                .stream()
                .map(mapper::languageToLanguageDTO)
                .toList();
    }

    public LanguageDTO findById(UUID id) {
        return languageRepository.findById(id)
                .map(mapper::languageToLanguageDTO)
                .orElseThrow(() -> new EntityNotFoundException("Language with id " + id + " not found"));
    }

    public Collection<LanguageDTO> findLanguagesByCriteria(List<SearchCriteria> criteria) {
        return languageRepository
                .findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10))
                .map(mapper::languageToLanguageDTO)
                .toList();
    }

    public LanguageDTO saveLanguage(LanguageDTO languageDTO) {
        return mapper.languageToLanguageDTO(languageRepository.save(mapper.languageDTOToLanguage(languageDTO)));
    }

    public LanguageDTO updateLanguage(LanguageDTO languageDTO) {
        return mapper.languageToLanguageDTO(languageRepository.save(mapper.languageDTOToLanguage(languageDTO)));
    }

    public void deleteLanguage(LanguageDTO languageDTO) {
        languageRepository.delete(mapper.languageDTOToLanguage(languageDTO));
    }
}
