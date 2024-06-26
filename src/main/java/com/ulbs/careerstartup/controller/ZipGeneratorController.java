package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.api.model.ApplicantsEmailRequest;
import com.ulbs.careerstartup.dto.FileDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.FileRepository;
import com.ulbs.careerstartup.service.EmailService;
import com.ulbs.careerstartup.util.ZipManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Setter
@Getter
@PreAuthorize("hasAnyAuthority('COMPANY_REPRESENTATIVE','ADMIN')")
public class ZipGeneratorController {

    private ZipManager zipManager;
    private FileRepository fileRepository;
    private Mapper mapper;
    private EmailService emailService;

    @GetMapping("/generate-zip/{emailRequest}")
    public void generatePdf(@PathVariable ApplicantsEmailRequest emailRequest){
        try {
            List<FileDTO> files = fileRepository.findAll().stream().map(mapper::fileToFileDTO).collect(Collectors.toList());
            FileDTO file = zipManager.createZipFromFiles(files);
            emailService.sendApplicants(new ApplicantsEmailRequest(emailRequest.toEmail(), emailRequest.subject(), emailRequest.name(), emailRequest.position(), file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
