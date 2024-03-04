package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.*;
import com.ulbs.careerstartup.entity.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    @Mapping(source = "writer", target = "writerDTO")
    @Mapping(source = "skill", target = "skillDTO")
    BibliographyDTO bibliographyToBibliographyDTO(Bibliography bibliography);

    @InheritInverseConfiguration(name = "bibliographyToBibliographyDTO")
    Bibliography bibliographyDTOToBibliography(BibliographyDTO bibliographyDTO);

    @Mapping(target = "jobHistoriesDTO", source = "jobHistories")
    @Mapping(target = "postedJobsDTO", source = "postedJobs")
    @Mapping(target = "reviewsDTO", source = "reviews")
    CompanyDTO companyToCompanyDTO(Company company);

    @InheritInverseConfiguration(name = "companyToCompanyDTO")
    Company companyDTOToCompany(CompanyDTO companyDTO);

    @Mapping(source = "specialization", target = "specializationDTO")
    @Mapping(source = "skills", target = "skillsDTO")
    CourseDTO courseToCourseDTO(Course course);

    @InheritInverseConfiguration(name = "courseToCourseDTO")
    Course courseDTOToCourse(CourseDTO courseDTO);

    @Mapping(source = "creator", target = "creatorDTO")
    @Mapping(source = "subscribers", target = "subscribersDTO")
    EventDTO eventToEventDTO(Event event);

    @InheritInverseConfiguration(name = "eventToEventDTO")
    Event eventDTOToEvent(EventDTO eventDTO);

    @Mapping(source = "user", target = "userDTO")
    ExperienceDTO experienceToExperienceDTO(Experience experience);

    @InheritInverseConfiguration(name = "experienceToExperienceDTO")
    Experience experienceDTOToExperience(ExperienceDTO experienceDTO);

    @Mapping(source = "specializations", target = "specializationsDTO")
    FacultyDTO facultyToFacultyDTO(Faculty faculty);

    @InheritInverseConfiguration(name = "facultyToFacultyDTO")
    Faculty facultyDTOToFaculty(FacultyDTO facultyDTO);

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "postedJob", target = "postedJobDTO")
    @Mapping(source = "id.candidateId", target = "candidateId")
    @Mapping(source = "id.jobId", target = "jobId")
    JobCandidatesDTO jobCandidatesToJobCandidatesDTO(JobCandidates jobCandidates);

    @InheritInverseConfiguration(name = "jobCandidatesToJobCandidatesDTO")
    JobCandidates jobCandidatesDTOToJobCandidates(JobCandidatesDTO jobCandidatesDTO);

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "company", target = "companyDTO")
    JobHistoryDTO jobHistoryToJobHistoryDTO(JobHistory jobHistory);

    @InheritInverseConfiguration(name = "jobHistoryToJobHistoryDTO")
    JobHistory jobHistoryDTOToJobHistory(JobHistoryDTO jobHistoryDTO);

    @Mapping(source = "user", target = "userDTO")
    LanguageDTO languageToLanguageDTO(Language language);

    @InheritInverseConfiguration(name = "languageToLanguageDTO")
    Language languageDTOToLanguage(LanguageDTO languageDTO);

    @Mapping(source = "sender", target = "senderDTO")
    @Mapping(source = "receiver", target = "receiverDTO")
    MessageDTO messageToMessageDTO(Message message);

    @InheritInverseConfiguration(name = "messageToMessageDTO")
    Message messageDTOToMessage(MessageDTO messageDTO);

    @Mapping(source = "user", target = "userDTO")
    NotificationDTO notificationToNotificationDTO(Notification notification);

    @InheritInverseConfiguration(name = "notificationToNotificationDTO")
    Notification notificationDTOToNotification(NotificationDTO notificationDTO);

    @Mapping(source = "company", target = "companyDTO")
    @Mapping(source = "jobCandidatesById", target = "jobCandidatesDTO")
    PostedJobDTO postedJobToPostedJobDTO(PostedJob postedJob);

    @InheritInverseConfiguration(name = "postedJobToPostedJobDTO")
    PostedJob postedJobDTOToPostedJob(PostedJobDTO postedJobDTO);

    @Mapping(source = "teacher", target = "teacherDTO")
    @Mapping(source = "student", target = "studentDTO")
    ReferralDTO referralToReferralDTO(Referral referral);

    @InheritInverseConfiguration(name = "referralToReferralDTO")
    Referral referralDTOToReferral(ReferralDTO referralDTO);

    @Mapping(source = "company", target = "companyDTO")
    ReviewDTO reviewToReviewDTO(Review review);

    @InheritInverseConfiguration(name = "reviewToReviewDTO")
    Review reviewDTOToReview(ReviewDTO reviewDTO);

    @Mapping(source = "bibliographies", target = "bibliographiesDTO")
    @Mapping(source = "userSkills", target = "userSkillsDTO")
    @Mapping(source = "courses", target = "coursesDTO")
    SkillDTO skillToSkillDTO(Skill skill);

    @InheritInverseConfiguration(name = "skillToSkillDTO")
    Skill skillDTOToSkill(SkillDTO skillDTO);

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "faculty", target = "facultyDTO")
    @Mapping(source = "courses", target = "coursesDTO")
    SpecializationDTO specializationToSpecializationDTO(Specialization specialization);

    @InheritInverseConfiguration(name = "specializationToSpecializationDTO")
    Specialization specializationDTOToSpecialization(SpecializationDTO specializationDTO);

    @Mapping(target = "createdEventsDTO", ignore = true)
    @Mapping(target = "bibliographiesDTO", source = "bibliographies")
    @Mapping(target = "experiencesDTO", ignore = true)
    @Mapping(target = "languagesDTO", source = "languages")
    @Mapping(target = "notificationsDTO", source = "notifications")
    @Mapping(target = "receivedReferralsDTO", source = "receivedReferrals")
    @Mapping(target = "specializationsDTO", source = "specializations")
    @Mapping(target = "skillsDTO", source = "skills")
    @Mapping(target = "jobHistoriesDTO", ignore = true)
    @Mapping(target = "jobCandidatesDTO", ignore = true)
    UserDTO userToUserDTO(User user);

    @InheritInverseConfiguration(name = "userToUserDTO")
    User userDTOToUser(UserDTO userDTO);

    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.skillId", target = "skillId")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "skill", target = "skillDTO")
    @Mapping(source = "proficiency", target = "proficiencyDTO")
    UserSkillsDTO userSkillsToUserSkillsDTO(UserSkills userSkills);

    @InheritInverseConfiguration(name = "userSkillsToUserSkillsDTO")
    UserSkills userSkillsDTOToUserSkills(UserSkillsDTO userSkillsDTO);

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
}
