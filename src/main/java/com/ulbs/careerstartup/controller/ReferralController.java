package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.ReferralApiDoc;
import com.ulbs.careerstartup.dto.ReferralDTO;
import com.ulbs.careerstartup.service.ReferralService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/referrals")
//@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
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

    @PostMapping
    public ReferralDTO saveReferral(@RequestBody ReferralDTO referralDTO) {
        return referralService.saveReferral(referralDTO);
    }

    @PatchMapping
    public ReferralDTO updateReferral(@RequestBody ReferralDTO referralDTO) {
        return referralService.updateReferral(referralDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteReferral(@PathVariable UUID id) {
        referralService.deleteReferral(id);
    }
}


