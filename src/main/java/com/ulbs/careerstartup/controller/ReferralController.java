package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.ReferralApiDoc;
import com.ulbs.careerstartup.dto.ReferralDTO;
import com.ulbs.careerstartup.service.ReferralService;
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

@AllArgsConstructor
@RequestMapping("/referrals")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Referral", description = "The Referral API")
public class ReferralController implements ReferralApiDoc {

    private ReferralService referralService;

    @GetMapping
    public ResponseEntity<Collection<ReferralDTO>> findAllReferrals() {
        return ResponseEntity.ok(referralService.findAllReferrals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReferralDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(referralService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<ReferralDTO>> findReferralsByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(referralService.findReferralsByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<ReferralDTO> saveReferral(@RequestBody ReferralDTO referralDTO) {
        return ResponseEntity.ok(referralService.saveReferral(referralDTO));
    }

    @PatchMapping
    public ResponseEntity<ReferralDTO> updateReferral(@RequestBody ReferralDTO referralDTO) {
        return ResponseEntity.ok(referralService.updateReferral(referralDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReferral(@RequestBody ReferralDTO referralDTO) {
        referralService.deleteReferral(referralDTO);
        return ResponseEntity.noContent().build();
    }
}


