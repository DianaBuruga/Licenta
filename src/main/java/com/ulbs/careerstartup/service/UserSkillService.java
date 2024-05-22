package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.UserSkillsDTO;
import com.ulbs.careerstartup.entity.UserSkills;
import com.ulbs.careerstartup.entity.pk.UserSkillPK;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.UserSkillsRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UserSkillService {
    private UserSkillsRepository userSkillsRepository;
    private Mapper mapper;

    @Transactional
    public UserSkillsDTO saveUserSkill(UserSkillsDTO userSkillsDTO) {
        UserSkills userSkills = mapper.userSkillsDTOToUserSkills(userSkillsDTO);
        userSkills = userSkillsRepository.save(userSkills);
        return mapper.userSkillsToUserSkillsDTO(userSkills);
    }

    @Transactional
    public void deleteUserSkill(UUID userId, UUID skillId) {
        userSkillsRepository.deleteById(new UserSkillPK(userId, skillId));
    }


    @Transactional
    public UserSkillsDTO updateUserSkill(UserSkillsDTO userSkillsDTO) {
        UserSkills userSkills = mapper.userSkillsDTOToUserSkills(userSkillsDTO);
        userSkills = userSkillsRepository.save(userSkills);
        return mapper.userSkillsToUserSkillsDTO(userSkills);
    }

    @Transactional
    public Collection<UserSkillsDTO> findByCriteria(List<SearchCriteria> criteria) {
        return userSkillsRepository
                .findAll(new GenericSpecification<>(criteria))
                .stream()
                .map(mapper::userSkillsToUserSkillsDTO)
                .toList();
    }
}
