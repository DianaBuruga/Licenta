package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.LanguageDTO;
import com.ulbs.careerstartup.entity.Language;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.LanguageRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
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

    public LanguageDTO findLanguageById(UUID id) {
        return languageRepository.findById(id)
                .map(mapper::languageToLanguageDTO)
                .orElseThrow(() -> new EntityNotFoundException("Language with id " + id + " not found"));
    }

    public Collection<LanguageDTO> findByCriteria(List<SearchCriteria> criteria) {
        return languageRepository
                .findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10))
                .map(mapper::languageToLanguageDTO)
                .toList();
    }

    public LanguageDTO saveLanguage(LanguageDTO languageDTO) {
        if (languageDTO.getId() != null) {
            throw new IllegalArgumentException("Language id must be null");
        }
        findByCriteria(List.of(new SearchCriteria("user.id", "=", languageDTO.getUserDTO().getId()), new SearchCriteria("name", "=", languageDTO.getName())))
                .stream()
                .findFirst()
                .ifPresent(x -> languageDTO.setId(x.getId()));
        Language language = mapper.languageDTOToLanguage(languageDTO);
        language.setUser(mapper.userDTOToUser(languageDTO.getUserDTO()));
        return mapper.languageToLanguageDTO(languageRepository.save(language));
    }

    public LanguageDTO updateLanguage(LanguageDTO languageDTO) {
        if (languageDTO.getId() == null) {
            throw new IllegalArgumentException("Language id must not be null");
        }
        Language language = mapper.languageDTOToLanguage(languageDTO);
        language.setUser(mapper.userDTOToUser(languageDTO.getUserDTO()));
        return mapper.languageToLanguageDTO(languageRepository.save(language));
    }

    @Transactional
    public void deleteLanguage(UUID id) {
        languageRepository.deleteById(id);
    }

    public boolean isOwner(UUID id, Principal principal) {
        LanguageDTO languageDTO = findByCriteria(List.of(new SearchCriteria("id", "=", id),
                new SearchCriteria("user.email", "=", principal.getName()))).stream().toList().get(0);
        return languageDTO != null;
    }
}
