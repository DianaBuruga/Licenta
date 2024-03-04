package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.ReferralDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.ReferralRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.NOT_FOUND;

public class ReferralService {

    private ReferralRepository referralRepository;
    private Mapper mapper;

    public Collection<ReferralDTO> findAllReferrals() {
        return referralRepository.findAll().stream().map(mapper::referralToReferralDTO).toList();
    }

    public ReferralDTO findById(UUID id) {
        return referralRepository
                .findById(id)
                .map(mapper::referralToReferralDTO)
                .orElseThrow(() -> new EntityNotFoundException("Referral with id " + id + NOT_FOUND));
    }

    public Collection<ReferralDTO> findReferralsByCriteria(List<SearchCriteria> searchCriteria) {
        return referralRepository
                .findAll(new GenericSpecification<>(searchCriteria), PageRequest.of(0, 10))
                .map(mapper::referralToReferralDTO)
                .toList();
    }

    public ReferralDTO saveReferral(ReferralDTO referralDTO) {
        return mapper.referralToReferralDTO(referralRepository.save(mapper.referralDTOToReferral(referralDTO)));
    }

    public ReferralDTO updateReferral(ReferralDTO referralDTO) {
        if (referralRepository.existsById(referralDTO.getId()))
            return mapper.referralToReferralDTO(referralRepository.save(mapper.referralDTOToReferral(referralDTO)));
        else throw new EntityNotFoundException("Referral with id " + referralDTO.getId() + NOT_FOUND);
    }

    @Transactional
    public void deleteReferral(ReferralDTO referralDTO) {
        if (referralRepository.existsById(referralDTO.getId())) {
            referralRepository.deleteById(referralDTO.getId());
        } else {
            throw new EntityNotFoundException("Referral with id " + referralDTO.getId() + NOT_FOUND);
        }
    }
}
