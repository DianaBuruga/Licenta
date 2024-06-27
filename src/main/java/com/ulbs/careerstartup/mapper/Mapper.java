package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.*;
import com.ulbs.careerstartup.entity.*;
import com.ulbs.careerstartup.entity.Notification;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@org.mapstruct.Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    @Mapping(target = "writerDTO", ignore = true)
    @Mapping(source = "skill", target = "skillDTO")
    @Mapping(source = "date", target = "date", qualifiedByName = "timestampToString")
    BibliographyDTO bibliographyToBibliographyDTO(Bibliography bibliography);

    @InheritInverseConfiguration(name = "bibliographyToBibliographyDTO")
    @Mapping(target = "date", source = "date", qualifiedByName = "stringToTimestamp")
    Bibliography bibliographyDTOToBibliography(BibliographyDTO bibliographyDTO);

    @Mapping(target = "jobHistoriesDTO", ignore = true)
    @Mapping(target = "postedJobsDTO", ignore = true)
    @Mapping(target = "reviewsDTO", source = "reviews")
    CompanyDTO companyToCompanyDTO(Company company);

    @InheritInverseConfiguration(name = "companyToCompanyDTO")
    Company companyDTOToCompany(CompanyDTO companyDTO);

    @InheritInverseConfiguration(name = "courseDTOToCourse")
    @Mapping(source = "specialization", target = "specializationDTO")
    CourseDTO courseToCourseDTO(Course course);

    @Mapping(source = "skillsDTO", target = "skills")
    Course courseDTOToCourse(CourseDTO courseDTO);

    @Mapping(target = "creatorDTO", ignore = true)
    @Mapping(target = "subscribersDTO", ignore = true)
    @Mapping(source = "date", target = "date", qualifiedByName = "timestampToString")
    EventDTO eventToEventDTO(Event event);

    @InheritInverseConfiguration(name = "eventToEventDTO")
    @Mapping(target = "date", source = "date", qualifiedByName = "stringToTimestamp")
    Event eventDTOToEvent(EventDTO eventDTO);

    @Mapping(source = "date", target = "date", qualifiedByName = "timestampToString")
    ExperienceDTO experienceToExperienceDTO(Experience experience);

    @InheritInverseConfiguration(name = "experienceToExperienceDTO")
    @Mapping(target = "date", source = "date", qualifiedByName = "stringToTimestamp")
    Experience experienceDTOToExperience(ExperienceDTO experienceDTO);

    FacultyDTO facultyToFacultyDTO(Faculty faculty);

    @InheritInverseConfiguration(name = "facultyToFacultyDTO")
    Faculty facultyDTOToFaculty(FacultyDTO facultyDTO);

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "id.candidateId", target = "candidateId")
    @Mapping(source = "id.jobId", target = "jobId")
    @Mapping(source = "applicationDate", target = "applicationDate", qualifiedByName = "timestampToString")
    JobCandidatesDTO jobCandidatesToJobCandidatesDTO(JobCandidates jobCandidates);

    @InheritInverseConfiguration(name = "jobCandidatesToJobCandidatesDTO")
    @Mapping(target = "applicationDate", source = "applicationDate", qualifiedByName = "stringToTimestamp")
    JobCandidates jobCandidatesDTOToJobCandidates(JobCandidatesDTO jobCandidatesDTO);

    @Mapping(source = "company", target = "companyDTO")
    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "timestampToString")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "timestampToString")
    JobHistoryDTO jobHistoryToJobHistoryDTO(JobHistory jobHistory);

    @InheritInverseConfiguration(name = "jobHistoryToJobHistoryDTO")
    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "stringToTimestamp")
    JobHistory jobHistoryDTOToJobHistory(JobHistoryDTO jobHistoryDTO);

    LanguageDTO languageToLanguageDTO(Language language);

    @InheritInverseConfiguration(name = "languageToLanguageDTO")
    Language languageDTOToLanguage(LanguageDTO languageDTO);

    @Mapping(source = "sendDate", target = "sendDate", qualifiedByName = "timestampToString")
    MessageDTO messageToMessageDTO(Message message);

    @InheritInverseConfiguration(name = "messageToMessageDTO")
    @Mapping(source = "sendDate", target = "sendDate", qualifiedByName = "stringToTimestamp")
    Message messageDTOToMessage(MessageDTO messageDTO);

    @Mapping(source = "date", target = "date", qualifiedByName = "timestampToString")
    NotificationDTO notificationToNotificationDTO(Notification notification);

    @InheritInverseConfiguration(name = "notificationToNotificationDTO")
    @Mapping(source = "date", target = "date", qualifiedByName = "stringToTimestamp")
    Notification notificationDTOToNotification(NotificationDTO notificationDTO);

    @Mapping(source = "company", target = "companyDTO")
    @Mapping(source = "jobCandidates", target = "jobCandidatesDTO")
    @Mapping(source = "postedDate", target = "postedDate", qualifiedByName = "timestampToString")
    @Mapping(source = "openUntil", target = "openUntil", qualifiedByName = "timestampToString")
    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "skills", target = "skillsDTO")
    PostedJobDTO postedJobToPostedJobDTO(PostedJob postedJob);

    @InheritInverseConfiguration(name = "postedJobToPostedJobDTO")
    @Mapping(source = "postedDate", target = "postedDate", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "openUntil", target = "openUntil", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "userDTO", target = "user")
    PostedJob postedJobDTOToPostedJob(PostedJobDTO postedJobDTO);

    ReferralDTO referralToReferralDTO(Referral referral);

    @InheritInverseConfiguration(name = "referralToReferralDTO")
    Referral referralDTOToReferral(ReferralDTO referralDTO);

    ReviewDTO reviewToReviewDTO(Review review);

    @InheritInverseConfiguration(name = "reviewToReviewDTO")
    Review reviewDTOToReview(ReviewDTO reviewDTO);

    SkillDTO skillToSkillDTO(Skill skill);

    @InheritInverseConfiguration(name = "skillToSkillDTO")
    Skill skillDTOToSkill(SkillDTO skillDTO);

    @Mapping(source = "startedDate", target = "startedDate", qualifiedByName = "timestampToString")
    @Mapping(source = "finishedDate", target = "finishedDate", qualifiedByName = "timestampToString")
    @Mapping(source = "faculty", target = "facultyDTO")
    SpecializationDTO specializationToSpecializationDTO(Specialization specialization);

    @InheritInverseConfiguration(name = "specializationToSpecializationDTO")
    @Mapping(source = "startedDate", target = "startedDate", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "finishedDate", target = "finishedDate", qualifiedByName = "stringToTimestamp")
    Specialization specializationDTOToSpecialization(SpecializationDTO specializationDTO);

    UserDTO userToUserDTO(User user);

    @InheritInverseConfiguration(name = "userToUserDTO")
    User userDTOToUser(UserDTO userDTO);

    @Mapping(target = "id.userId", source = "userId")
    @Mapping(target = "id.skillId", source = "skillId")
    @Mapping(target = "skill", source = "skillDTO")
    UserSkills userSkillsDTOToUserSkills(UserSkillsDTO userSkillsDTO);

    @InheritInverseConfiguration(name = "userSkillsDTOToUserSkills")
    UserSkillsDTO userSkillsToUserSkillsDTO(UserSkills userSkills);

    @AfterMapping
    default void afterUserMapping(@MappingTarget User user) {
        if (Objects.nonNull(user.getBibliographies())) {
            user.getBibliographies().forEach(bib -> bib.setWriter(user));
        }
        if (Objects.nonNull(user.getSkills())) {
            user.getSkills().forEach(userSkills -> userSkills.setUser(user));
        }
        if (Objects.nonNull(user.getSpecializations())) {
            user.getSpecializations().forEach(specialization -> specialization.setUser(user));
        }
        if (Objects.nonNull(user.getLanguages())) {
            user.getLanguages().forEach(language -> language.setUser(user));
        }
        if (Objects.nonNull(user.getJobCandidates())) {
            user.getJobCandidates().forEach(jobCandidates -> jobCandidates.setUser(user));
        }
        if (Objects.nonNull(user.getJobHistories())) {
            user.getJobHistories().forEach(jobHistory -> jobHistory.setUser(user));
        }
        if (Objects.nonNull(user.getEvents())) {
            user.getEvents().forEach(event -> event.setCreator(user));
        }
        if (Objects.nonNull(user.getEventSubscribers())) {
            user.getEventSubscribers().forEach(event -> event.getSubscribers().add(user));
        }
        if (Objects.nonNull(user.getExperiences())) {
            user.getExperiences().forEach(exp -> exp.setUser(user));
        }
        if (Objects.nonNull(user.getLanguages())) {
            user.getLanguages().forEach(language -> language.setUser(user));
        }
        if (Objects.nonNull(user.getSentMessages())) {
            user.getSentMessages().forEach(message -> message.setSender(user));
        }
        if (Objects.nonNull(user.getReceivedMessages())) {
            user.getReceivedMessages().forEach(message -> message.setReceiver(user));
        }
        if (Objects.nonNull(user.getNotifications())) {
            user.getNotifications().forEach(notification -> notification.setUser(user));
        }
        if (Objects.nonNull(user.getWrittenReferrals())) {
            user.getWrittenReferrals().forEach(referral -> referral.setTeacher(user));
        }
        if (Objects.nonNull(user.getReceivedReferrals())) {
            user.getReceivedReferrals().forEach(referral -> referral.setStudent(user));
        }
        if (Objects.nonNull(user.getSkills())) {
            user.getSkills().forEach(userSkills -> userSkills.setUser(user));
        }
    }

    @Mapping(source = "id.tableId", target = "tableId")
    @Mapping(source = "id.tableName", target = "tableName")
    FileDTO fileToFileDTO(File file);

    @Mapping(target = "id.tableId", source = "tableId")
    @Mapping(target = "id.tableName", source = "tableName")
    File fileDTOToFile(FileDTO fileDTO);


    default File multipartFileToFile(MultipartFile multipartFile) throws IOException {
        return File.builder()
                .name(StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename())))
                .content(multipartFile.getBytes())
                .build();
    }

    @Named("timestampToString")
    default String mapTimestampToString(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return localDateTime.toLocalDate().format(formatter);
    }

    @Named("stringToTimestamp")
    default Timestamp mapStringToTimestamp(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return null;
        }

        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, isoFormatter);

        return Timestamp.valueOf(localDateTime);
    }

    default ReferralDTO referralWithUsersToReferralDTO(Referral referral) {
        ReferralDTO referralDTO = referralToReferralDTO(referral);
        referralDTO.setStudentDTO(userToUserDTO(referral.getStudent()));
        referralDTO.setTeacherDTO(userToUserDTO(referral.getTeacher()));
        return referralDTO;
    }

    default Referral referralDTOWithUsersToReferral(ReferralDTO referralDTO) {
        Referral referral = referralDTOToReferral(referralDTO);
        referral.setTeacher(userDTOToUser(referralDTO.getTeacherDTO()));
        referral.setStudent(userDTOToUser(referralDTO.getStudentDTO()));
        return referral;
    }
}
