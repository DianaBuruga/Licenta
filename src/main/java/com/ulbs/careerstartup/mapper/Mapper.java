package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.*;
import com.ulbs.careerstartup.entity.*;
import com.ulbs.careerstartup.util.CompanyUtil;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@org.mapstruct.Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    //@Mapping(source = "writer", target = "writerDTO")
    @Mapping(target = "writerDTO", ignore = true)
    @Mapping(source = "skill", target = "skillDTO")
    @Mapping(source = "date", target = "date", qualifiedByName = "timestampToString")
    BibliographyDTO bibliographyToBibliographyDTO(Bibliography bibliography);

    @InheritInverseConfiguration(name = "bibliographyToBibliographyDTO")
    @Mapping(target = "date", source = "date", qualifiedByName = "stringToTimestamp")
    Bibliography bibliographyDTOToBibliography(BibliographyDTO bibliographyDTO);

    //  @Mapping(target = "jobHistoriesDTO", source = "jobHistories")
//    @Mapping(target = "postedJobsDTO", source = "postedJobs")
    @Mapping(target = "jobHistoriesDTO", ignore = true)
    @Mapping(target = "postedJobsDTO", ignore = true)
    @Mapping(target = "reviewsDTO", source = "reviews")
    CompanyDTO companyToCompanyDTO(Company company);

    @InheritInverseConfiguration(name = "companyToCompanyDTO")
    Company companyDTOToCompany(CompanyDTO companyDTO);

    @Mapping(source = "specialization", target = "specializationDTO")
    @Mapping(source = "skills", target = "skillsDTO")
    CourseDTO courseToCourseDTO(Course course);

    @InheritInverseConfiguration(name = "courseToCourseDTO")
    Course courseDTOToCourse(CourseDTO courseDTO);

    //    @Mapping(source = "creator", target = "creatorDTO")
//    @Mapping(source = "subscribers", target = "subscribersDTO")
    @Mapping(target = "creatorDTO", ignore = true)
    @Mapping(target = "subscribersDTO", ignore = true)
    @Mapping(source = "date", target = "date", qualifiedByName = "timestampToString")
    EventDTO eventToEventDTO(Event event);

    @InheritInverseConfiguration(name = "eventToEventDTO")
    @Mapping(target = "date", source = "date", qualifiedByName = "stringToTimestamp")
    Event eventDTOToEvent(EventDTO eventDTO);

    //    @Mapping(source = "user", target = "userDTO")

    @Mapping(source = "date", target = "date", qualifiedByName = "timestampToString")
    ExperienceDTO experienceToExperienceDTO(Experience experience);

    @InheritInverseConfiguration(name = "experienceToExperienceDTO")
    @Mapping(target = "date", source = "date", qualifiedByName = "stringToTimestamp")
    Experience experienceDTOToExperience(ExperienceDTO experienceDTO);

    @Mapping(source = "specializations", target = "specializationsDTO")
    FacultyDTO facultyToFacultyDTO(Faculty faculty);

    @InheritInverseConfiguration(name = "facultyToFacultyDTO")
    Faculty facultyDTOToFaculty(FacultyDTO facultyDTO);

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "postedJob", target = "postedJobDTO")
    @Mapping(source = "id.candidateId", target = "candidateId")
    @Mapping(source = "id.jobId", target = "jobId")
    @Mapping(source = "applicationDate", target = "applicationDate", qualifiedByName = "timestampToString")
    JobCandidatesDTO jobCandidatesToJobCandidatesDTO(JobCandidates jobCandidates);

    @InheritInverseConfiguration(name = "jobCandidatesToJobCandidatesDTO")
    @Mapping(target = "applicationDate", source = "applicationDate", qualifiedByName = "stringToTimestamp")
    JobCandidates jobCandidatesDTOToJobCandidates(JobCandidatesDTO jobCandidatesDTO);

    //    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "company", target = "companyDTO")
    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "timestampToString")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "timestampToString")
    JobHistoryDTO jobHistoryToJobHistoryDTO(JobHistory jobHistory);

    @InheritInverseConfiguration(name = "jobHistoryToJobHistoryDTO")
    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "stringToTimestamp")
    JobHistory jobHistoryDTOToJobHistory(JobHistoryDTO jobHistoryDTO);

    //@Mapping(source = "user", target = "userDTO")
    LanguageDTO languageToLanguageDTO(Language language);

    @InheritInverseConfiguration(name = "languageToLanguageDTO")
    Language languageDTOToLanguage(LanguageDTO languageDTO);

    //    @Mapping(source = "sender", target = "senderDTO")
//    @Mapping(source = "receiver", target = "receiverDTO")

    @Mapping(source = "sendDate", target = "sendDate", qualifiedByName = "timestampToString")
    MessageDTO messageToMessageDTO(Message message);

    @InheritInverseConfiguration(name = "messageToMessageDTO")
    @Mapping(source = "sendDate", target = "sendDate", qualifiedByName = "stringToTimestamp")
    Message messageDTOToMessage(MessageDTO messageDTO);

    // @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "date", target = "date", qualifiedByName = "timestampToString")
    NotificationDTO notificationToNotificationDTO(Notification notification);

    @InheritInverseConfiguration(name = "notificationToNotificationDTO")
    @Mapping(source = "date", target = "date", qualifiedByName = "stringToTimestamp")
    Notification notificationDTOToNotification(NotificationDTO notificationDTO);

    @Mapping(source = "company", target = "companyDTO")
    @Mapping(source = "jobCandidatesById", target = "jobCandidatesDTO")
    @Mapping(source = "postedDate", target = "postedDate", qualifiedByName = "timestampToString")
    @Mapping(source = "openUntil", target = "openUntil", qualifiedByName = "timestampToString")
    PostedJobDTO postedJobToPostedJobDTO(PostedJob postedJob);

    @InheritInverseConfiguration(name = "postedJobToPostedJobDTO")
    @Mapping(source = "postedDate", target = "postedDate", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "openUntil", target = "openUntil", qualifiedByName = "stringToTimestamp")
    PostedJob postedJobDTOToPostedJob(PostedJobDTO postedJobDTO);

    //    @Mapping(source = "teacher", target = "teacherDTO")
//    @Mapping(source = "student", target = "studentDTO")
    ReferralDTO referralToReferralDTO(Referral referral);

    @InheritInverseConfiguration(name = "referralToReferralDTO")
    Referral referralDTOToReferral(ReferralDTO referralDTO);

    //  @Mapping(source = "company", target = "companyDTO")
    ReviewDTO reviewToReviewDTO(Review review);

    @InheritInverseConfiguration(name = "reviewToReviewDTO")
    Review reviewDTOToReview(ReviewDTO reviewDTO);

    //    @Mapping(source = "bibliographies", target = "bibliographiesDTO")
//    @Mapping(source = "userSkills", target = "userSkillsDTO")
//    @Mapping(source = "courses", target = "coursesDTO")
    SkillDTO skillToSkillDTO(Skill skill);

    @InheritInverseConfiguration(name = "skillToSkillDTO")
    Skill skillDTOToSkill(SkillDTO skillDTO);

    //    @Mapping(source = "user", target = "userDTO")
    //  @Mapping(source = "faculty", target = "facultyDTO")
    // @Mapping(source = "courses", target = "coursesDTO")

    @Mapping(source = "startedDate", target = "startedDate", qualifiedByName = "timestampToString")
    @Mapping(source = "finishedDate", target = "finishedDate", qualifiedByName = "timestampToString")
    SpecializationDTO specializationToSpecializationDTO(Specialization specialization);

    @InheritInverseConfiguration(name = "specializationToSpecializationDTO")
    @Mapping(source = "startedDate", target = "startedDate", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "finishedDate", target = "finishedDate", qualifiedByName = "stringToTimestamp")
    Specialization specializationDTOToSpecialization(SpecializationDTO specializationDTO);

    // @Mapping(target = "createdEventsDTO", ignore = true)
    @Mapping(target = "bibliographiesDTO", source = "bibliographies")
    @Mapping(target = "experiencesDTO", source = "experiences")
    @Mapping(target = "languagesDTO", source = "languages")
    // @Mapping(target = "notificationsDTO", source = "notifications")
    @Mapping(target = "receivedReferralsDTO", source = "receivedReferrals")
    @Mapping(target = "specializationsDTO", source = "specializations")
    @Mapping(target = "skillsDTO", source = "skills")
    @Mapping(target = "jobHistoriesDTO", source = "jobHistories")
    //@Mapping(target = "jobCandidatesDTO", source = "jobCandidates")
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
    default void afterCompanyDtoMapping(@MappingTarget CompanyDTO companyDTO) {
        companyDTO.setLogoUrl(new CompanyUtil().getFaviconUrl(companyDTO.getWebsite()));
    }

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
        if(Objects.nonNull(user.getSkills())){
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

    default UserDTO oidcUserToUserDTO(OidcUser oidcUser) {
        return UserDTO.builder()
                .name(oidcUser.getFullName())
                .email(oidcUser.getEmail())
                .phone(oidcUser.getPhoneNumber())
                .build();
    }

    @Named("timestampToString")
    default String mapTimestampToString(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        return timestamp.toInstant().atZone(ZoneId.of("Europe/Bucharest")).format(DateTimeFormatter.ISO_INSTANT);
    }

    @Named("stringToTimestamp")
    default Timestamp mapStringToTimestamp(String dateTimeStr) {
        if (dateTimeStr == null) {
            return null;
        } else if (dateTimeStr.equals("")) {
            return null;
        }

        return Timestamp.from(ZonedDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant());
    }
}
