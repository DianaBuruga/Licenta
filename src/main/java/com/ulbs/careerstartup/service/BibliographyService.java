package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.BibliographyDTO;
import com.ulbs.careerstartup.entity.Bibliography;
import com.ulbs.careerstartup.entity.Skill;
import com.ulbs.careerstartup.entity.User;
import com.ulbs.careerstartup.mapper.BibliographyMapper;
import com.ulbs.careerstartup.repository.BibliographyRepository;
import com.ulbs.careerstartup.repository.SkillRepository;
import com.ulbs.careerstartup.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ExceptionMessages.NOT_FOUND_MESSAGE;

@Service
@AllArgsConstructor
public class BibliographyService {
    private static final BibliographyMapper bibliographyMapper = BibliographyMapper.INSTANCE;
    private UserRepository userRepository;
    private SkillRepository skillRepository;
    private BibliographyRepository bibliographyRepository;

    public BibliographyDTO saveBibliography(BibliographyDTO bibliographyDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UUID skillId = bibliographyDTO.getSkillDTO().getId();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, "user", "email", email)));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, "skill", "id", skillId)));

        Bibliography bibliography = bibliographyMapper.bibliographyDTOToBibliography(bibliographyDTO);
        bibliography.setWriter(user);
        bibliography.setSkill(skill);
        bibliography.setDate(Timestamp.from(Instant.now()));

        user.getBibliographies().add(bibliography);
        skill.getBibliographies().add(bibliography);

        return bibliographyMapper.bibliographyToBibliographyDTO(
                bibliographyRepository.save(bibliography));
    }

    public BibliographyDTO updateBibliography(BibliographyDTO bibliographyDTO) {
        Bibliography bibliography = bibliographyRepository.findById(bibliographyDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, "bibliography", "id", bibliographyDTO.getId())));

        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(bibliography.getWriter().getEmail())) {
            throw new SecurityException("You are not allowed to update this bibliography");
        }

        bibliography.setText(bibliographyDTO.getText());
        bibliography.setTitle(bibliographyDTO.getTitle());
        skillRepository.findById(bibliographyDTO.getSkillDTO().getId())
                .ifPresent(bibliography::setSkill);

        return bibliographyMapper.bibliographyToBibliographyDTO(
                bibliographyRepository.save(bibliography));
    }

    public Collection<BibliographyDTO> findAllBibliographies() {
        return bibliographyRepository.findAll()
                .stream()
                .map(bibliographyMapper::bibliographyToBibliographyDTO)
                .toList();
    }

    public Collection<BibliographyDTO> getBibliographiesBySkillIds(List<UUID> skillIds) {
        return bibliographyRepository.findBySkillIdIn(skillIds)
                .stream()
                .map(bibliographyMapper::bibliographyToBibliographyDTO)
                .toList();
    }
}
