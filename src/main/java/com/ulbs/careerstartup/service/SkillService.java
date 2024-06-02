package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.SkillDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.SkillRepository;
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

@Service
@AllArgsConstructor
@Slf4j
public class SkillService {

    private SkillRepository skillRepository;
    private Mapper mapper;

    public Collection<SkillDTO> findAllSkills() {
        return skillRepository
                .findAll()
                .stream()
                .map(mapper::skillToSkillDTO)
                .toList();
    }

    public SkillDTO findSkillById(UUID id) {
        return skillRepository.findById(id)
                .map(mapper::skillToSkillDTO)
                .orElseThrow(() -> new EntityNotFoundException("Skill with id " + id + " not found"));
    }

    public Collection<SkillDTO> findByCriteria(List<SearchCriteria> criteria) {
        return skillRepository
                .findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10))
                .map(mapper::skillToSkillDTO)
                .toList();
    }

    public SkillDTO saveSkill(SkillDTO skillDTO) {
        return mapper.skillToSkillDTO(skillRepository.save(mapper.skillDTOToSkill(skillDTO)));
    }

    public SkillDTO updateSkill(SkillDTO skillDTO) {
        return mapper.skillToSkillDTO(skillRepository.save(mapper.skillDTOToSkill(skillDTO)));
    }

    public void deleteSkill(UUID id) {
        skillRepository.deleteById(id);
    }
}
