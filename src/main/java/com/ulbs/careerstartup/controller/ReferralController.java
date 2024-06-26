package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.ReferralApiDoc;
import com.ulbs.careerstartup.dto.ReferralDTO;
import com.ulbs.careerstartup.security.isOwnerRole.referral.IsReferralOwner;
import com.ulbs.careerstartup.service.ReferralService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/referrals")
@Tag(name = "Referral", description = "The Referral API")
public class ReferralController implements ReferralApiDoc {

    private ReferralService referralService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public Collection<ReferralDTO> findAllReferrals() {
        return referralService.findAllReferrals();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public ReferralDTO findReferralById(@PathVariable UUID id) {
        return referralService.findReferralById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public ReferralDTO saveReferral(@RequestBody ReferralDTO referralDTO) {
        return referralService.saveReferral(referralDTO);
    }

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    @IsReferralOwner
    public ReferralDTO updateReferral(@RequestBody ReferralDTO referralDTO) {
        return referralService.updateReferral(referralDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'ADMIN')")
    @IsReferralOwner
    public void deleteReferral(@PathVariable UUID id) {
        referralService.deleteReferral(id);
    }
}


