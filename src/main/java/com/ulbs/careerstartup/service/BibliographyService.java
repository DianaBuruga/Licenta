package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.BibliographyDTO;
import com.ulbs.careerstartup.entity.Bibliography;
import com.ulbs.careerstartup.entity.Skill;
import com.ulbs.careerstartup.entity.User;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.BibliographyRepository;
import com.ulbs.careerstartup.repository.SkillRepository;
import com.ulbs.careerstartup.repository.UserRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ExceptionMessages.NOT_FOUND_MESSAGE;

@Service
@AllArgsConstructor
public class BibliographyService {
    private Mapper mapper;
    private UserRepository userRepository;
    private SkillRepository skillRepository;
    private BibliographyRepository bibliographyRepository;

    public BibliographyDTO saveBibliography(BibliographyDTO bibliographyDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String skillName = bibliographyDTO.getSkillDTO().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, "user", "email", email)));

        Bibliography bibliography = mapper.bibliographyDTOToBibliography(bibliographyDTO);
        bibliography.setWriter(user);
        bibliography.setDate(Timestamp.from(Instant.now()));
        skillRepository.findByName(bibliographyDTO.getSkillDTO().getName())
                .ifPresentOrElse(
                        bibliography::setSkill,
                        () -> skillRepository.save(Skill.builder().name(skillName).build())
                );
        user.getBibliographies().add(bibliography);

        bibliography = bibliographyRepository.save(bibliography);
        bibliographyDTO = mapper.bibliographyToBibliographyDTO(bibliography);
        bibliographyDTO.setWriterDTO(mapper.userToUserDTO(user));
        return bibliographyDTO;
    }

    public BibliographyDTO updateBibliography(BibliographyDTO bibliographyDTO) {
        UUID id = bibliographyDTO.getId();
        String skill = bibliographyDTO.getSkillDTO().getName();
        Bibliography bibliography = bibliographyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, "bibliography", "id", id)));

        if (bibliographyDTO.getText() != null) {
            bibliography.setText(bibliographyDTO.getText());
        }

        if (bibliographyDTO.getTitle() != null) {
            bibliography.setTitle(bibliographyDTO.getTitle());
        }

        if (bibliographyDTO.getSkillDTO() != null) {
            skillRepository.findByName(bibliographyDTO.getSkillDTO().getName())
                    .ifPresentOrElse(
                            bibliography::setSkill,
                            () -> skillRepository.save(Skill.builder().name(skill).build())
                    );
        }

        bibliography = bibliographyRepository.save(bibliography);
        bibliographyDTO = mapper.bibliographyToBibliographyDTO(bibliography);
        bibliographyDTO.setWriterDTO(mapper.userToUserDTO(bibliography.getWriter()));
        return bibliographyDTO;
    }

    public Collection<BibliographyDTO> findAllBibliographies() {
        return bibliographyRepository.findAll()
                .stream()
                .map(bibliogtraphy -> {
                    BibliographyDTO bibliographyDTO = mapper.bibliographyToBibliographyDTO(bibliogtraphy);
                    bibliographyDTO.setWriterDTO(mapper.userToUserDTO(bibliogtraphy.getWriter()));
                    return bibliographyDTO;
                })
                .toList();
    }

    public Collection<BibliographyDTO> findBibliographiesBySkillIds(List<UUID> skillIds) {
        return bibliographyRepository.findBySkillIdIn(skillIds)
                .stream()
                .map(mapper::bibliographyToBibliographyDTO)
                .toList();
    }

    public Collection<BibliographyDTO> findByCriteria(List<SearchCriteria> searchCriteria) {
        return bibliographyRepository
                .findAll(new GenericSpecification<>(searchCriteria))
                .stream()
                .map(bibliogtraphy -> {
                    BibliographyDTO bibliographyDTO = mapper.bibliographyToBibliographyDTO(bibliogtraphy);
                    bibliographyDTO.setWriterDTO(mapper.userToUserDTO(bibliogtraphy.getWriter()));
                    return bibliographyDTO;
                })
                .toList();
    }

    public void deleteBibliography(UUID id) {
        bibliographyRepository.deleteById(id);
    }

    public boolean isOwner(UUID id, Principal principal) {
        Bibliography bibliography = bibliographyRepository.findById(id).orElse(null);
        return bibliography != null && principal.getName().equals(bibliography.getWriter().getEmail());
    }
}
