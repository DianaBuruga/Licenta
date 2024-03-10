package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.ReferralApiDoc;
import com.ulbs.careerstartup.dto.ReferralDTO;
import com.ulbs.careerstartup.service.ReferralService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.BY_CRITERIA;

@AllArgsConstructor
@RequestMapping("/referrals")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Referral", description = "The Referral API")
public class ReferralController implements ReferralApiDoc {

    private ReferralService referralService;

    @GetMapping
    public Collection<ReferralDTO> findAllReferrals() {
        return referralService.findAllReferrals();
    }

    @GetMapping("/{id}")
    public ReferralDTO findReferralById(@PathVariable UUID id) {
        return referralService.findReferralById(id);
    }

    @GetMapping(BY_CRITERIA)
    public Collection<ReferralDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return referralService.findReferralsByCriteria(criteria);
    }

    @PostMapping
    public ReferralDTO saveReferral(@RequestBody ReferralDTO referralDTO) {
        return referralService.saveReferral(referralDTO);
    }

    @PatchMapping
    public ReferralDTO updateReferral(@RequestBody ReferralDTO referralDTO) {
        return referralService.updateReferral(referralDTO);
    }

    @DeleteMapping
    public void deleteReferral(@RequestBody ReferralDTO referralDTO) {
        referralService.deleteReferral(referralDTO);
    }
}


