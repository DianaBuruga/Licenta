package com.ulbs.careerstartup.util;

import com.lowagie.text.DocumentException;
import com.ulbs.careerstartup.api.model.ApplicantsEmailRequest;
import com.ulbs.careerstartup.dto.FileDTO;
import com.ulbs.careerstartup.entity.JobCandidates;
import com.ulbs.careerstartup.entity.PostedJob;
import com.ulbs.careerstartup.service.EmailService;
import com.ulbs.careerstartup.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class RecruiterNotificator {
    private static final String APPLICANTS = "Applicants";
    private UserService userService;
    private ZipManager zipManager;
    private EmailService emailService;

    public void notify(PostedJob job) throws DocumentException, IOException {
        List<FileDTO> files = new ArrayList<>();

        for (JobCandidates candidate : job.getJobCandidates()) {
            files.add(userService.generateCV(candidate.getId().getCandidateId()));
        }

        FileDTO file = zipManager.createZipFromFiles(files);
        emailService.sendApplicants(new ApplicantsEmailRequest("dianaelena.buruga@ulbsibiu.ro", APPLICANTS, job.getUser().getName(), job.getPosition(), file));
    }
}
