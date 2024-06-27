package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.ReferralDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.ReferralRepository;
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

import static com.ulbs.careerstartup.constant.Constants.NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class ReferralService {

    private ReferralRepository referralRepository;
    private Mapper mapper;

    public Collection<ReferralDTO> findAllReferrals() {
        return referralRepository.findAll().stream().map(mapper::referralWithUsersToReferralDTO).toList();
    }


    public ReferralDTO findReferralById(UUID id) {
        return referralRepository
                .findById(id)
                .map(mapper::referralWithUsersToReferralDTO)
                .orElseThrow(() -> new EntityNotFoundException("Referral with id " + id + NOT_FOUND));
    }

    public Collection<ReferralDTO> findByCriteria(List<SearchCriteria> searchCriteria) {
        return referralRepository
                .findAll(new GenericSpecification<>(searchCriteria), PageRequest.of(0, 10))
                .map(mapper::referralWithUsersToReferralDTO)
                .toList();
    }

    public ReferralDTO saveReferral(ReferralDTO referralDTO) {
        return mapper.referralWithUsersToReferralDTO(referralRepository.save(mapper.referralDTOWithUsersToReferral(referralDTO)));
    }

    public ReferralDTO updateReferral(ReferralDTO referralDTO) {
        if (referralRepository.existsById(referralDTO.getId())) {
            return mapper.referralWithUsersToReferralDTO(referralRepository.save(mapper.referralDTOWithUsersToReferral(referralDTO)));
        } else throw new EntityNotFoundException("Referral with id " + referralDTO.getId() + NOT_FOUND);
    }

    @Transactional
    public void deleteReferral(UUID id) {
        referralRepository.deleteById(id);
    }

    public boolean isOwner(UUID id, Principal principal) {
        return findByCriteria(List.of(new SearchCriteria("id", "=", id),
                new SearchCriteria("teacher.email","=",principal.getName())))
                .stream().toList().get(0) != null;
    }
}
