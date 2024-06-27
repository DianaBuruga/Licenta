package com.ulbs.careerstartup.util;

import com.lowagie.text.DocumentException;
import com.ulbs.careerstartup.constant.ExperienceType;
import com.ulbs.careerstartup.constant.FileType;
import com.ulbs.careerstartup.dto.*;
import com.ulbs.careerstartup.entity.*;
import com.ulbs.careerstartup.entity.pk.FilePK;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.FileRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Setter
@Getter
public class CVGenerator {
    private static final String TEMPLATE = "cv.html";
    private final Mapper mapper;
    private FileRepository fileRepository;
    private SpringTemplateEngine templateEngine;

    public File generateCV(User user) throws DocumentException, IOException {
        String html = parseThymeleafTemplate(user);
        return generatePdfFromHtml(html, user);
    }

    private File generatePdfFromHtml(String html, User user) throws IOException, DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(byteArrayOutputStream);
        byteArrayOutputStream.close();

        String name = String.format("CV-%s%s", user.getName().trim().replace(" ", "_"), ".pdf");
        return fileRepository.save(new File(new FilePK(user.getId(), "user", FileType.CV), name, byteArrayOutputStream.toByteArray()));
    }

    private String parseThymeleafTemplate(User user) {
        Context context = createContext(user);

        return templateEngine.process("cv/" + TEMPLATE, context);
    }

    public Context createContext(User user) {
        Context context = new Context();
        Map<String, Object> properties = new HashMap<>();
        properties.put("cvPhotoAlt", "cv-photo");
        properties.put("cvPhotoTitle", "cv-photo");
        properties.put("jobHistories",
                user.getJobHistories()
                        .stream()
                        .sorted(Comparator.comparing(JobHistory::getStartDate).reversed())
                        .map(jobHistory -> {
                            JobHistoryDTO jobHistoryDTO = mapper.jobHistoryToJobHistoryDTO(jobHistory);
                            jobHistoryDTO.setCompanyDTO(mapper.companyToCompanyDTO(jobHistory.getCompany()));
                            return jobHistoryDTO;
                        })
                        .collect(Collectors.toList()));
        properties.put("projects",
                user.getExperiences()
                        .stream()
                        .filter(experience -> experience.getType().equals(ExperienceType.PROJECT))
                        .sorted(Comparator.comparing(Experience::getDate).reversed())
                        .collect(Collectors.toList()));
        properties.put("certifications",
                user.getExperiences()
                        .stream()
                        .filter(experience -> experience.getType().equals(ExperienceType.ACCREDITATION))
                        .sorted(Comparator.comparing(Experience::getDate).reversed())
                        .collect(Collectors.toList()));
        properties.put("educations",
                user.getSpecializations()
                        .stream()
                        .sorted(Comparator.comparing(Specialization::getStartedDate).reversed())
                        .collect(Collectors.toList()));
        properties.put("user", user);

        context.setVariables(properties);
        return context;
    }

    public Date getDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.parse(dateString);
    }

    public File generateCV(UserDTO user) throws DocumentException, IOException {
        String html = parseThymeleafTemplate(user);
        return generatePdfFromHtml(html, user);
    }

    private File generatePdfFromHtml(String html, UserDTO user) throws IOException, DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(byteArrayOutputStream);
        byteArrayOutputStream.close();

        String name = String.format("CV-%s%s", user.getName().trim().replace(" ", "_"), ".pdf");
        return fileRepository.save(new File(new FilePK(user.getId(), "user", FileType.CV), name, byteArrayOutputStream.toByteArray()));
    }

    private String parseThymeleafTemplate(UserDTO user) {
        String template = "cv2.html";
        Context context = createContext(user);

        return templateEngine.process("cv/" + template, context);
    }

    public Context createContext(UserDTO user) {
        Context context = new Context();
        Map<String, Object> properties = new HashMap<>();
        properties.put("cvPhotoAlt", "cv-photo");
        properties.put("cvPhotoTitle", "cv-photo");
        properties.put("jobHistories", user.getJobHistoriesDTO()
                .stream()
                .sorted((o1, o2) -> {
                    try {
                        return getDate(o1.getStartDate()).compareTo(getDate(o2.getStartDate()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }));
        properties.put("projects",
                user.getExperiencesDTO()
                        .stream()
                        .filter(experience -> experience.getType().equals(ExperienceType.PROJECT))
                        .sorted(Comparator.comparing(
                                (ExperienceDTO o) -> {
                                    try {
                                        return getDate(o.getDate());
                                    } catch (ParseException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                ).reversed())
                        .collect(Collectors.toList()));
        properties.put("certifications",
                user.getExperiencesDTO()
                        .stream()
                        .filter(experience -> experience.getType().equals(ExperienceType.ACCREDITATION))
                        .sorted(Comparator.comparing(
                                (ExperienceDTO o) -> {
                                    try {
                                        return getDate(o.getDate());
                                    } catch (ParseException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                        ).reversed())
                        .collect(Collectors.toList()));
        properties.put("educations",
                user.getSpecializationsDTO().
                stream()
                        .sorted(Comparator.comparing(
                                (SpecializationDTO o) -> {
                                    try {
                                        return getDate(o.getStartedDate());
                                    } catch (ParseException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                        ).reversed())
                        .collect(Collectors.toList()));
        properties.put("user", user);

        context.setVariables(properties);
        return context;
    }



}