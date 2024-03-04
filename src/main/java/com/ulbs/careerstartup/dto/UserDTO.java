package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.Constants;
import com.ulbs.careerstartup.constant.Role;
import com.ulbs.careerstartup.constant.UserStatus;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.INVALID_EMAIL;
import static com.ulbs.careerstartup.constant.ValidationMessages.INVALID_PHONE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private UUID id;

    @NotNull
    @Email(message = INVALID_EMAIL)
    private String email;

    @NotNull
    private Role role;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = Constants.ROMANIAN_PHONE_REGEX, message = INVALID_PHONE)
    private String phone;

    @NotNull
    @URL
    private String website;

    @NotNull
    private String description;

    @NotNull
    private UserStatus status;

    private FileDTO profilePhoto;

    @NotNull
    private Collection<BibliographyDTO> bibliographiesDTO;

    @NotNull
    private Collection<EventDTO> createdEventsDTO;

    @NotNull
    private Collection<EventDTO> eventSubscribersDTO;

    @NotNull
    private Collection<ExperienceDTO> experiencesDTO;

    @NotNull
    private Collection<JobCandidatesDTO> jobCandidatesDTO;

    @NotNull
    private Collection<JobHistoryDTO> jobHistoriesDTO;

    @NotNull
    private Collection<LanguageDTO> languagesDTO;

    @NotNull
    private Collection<MessageDTO> sentMessagesDTO;

    @NotNull
    private Collection<MessageDTO> receivedMessagesDTO;

    @NotNull
    private Collection<NotificationDTO> notificationsDTO;

    @NotNull
    private Collection<ReferralDTO> writtenReferralsDTO;

    @NotNull
    private Collection<ReferralDTO> receivedReferralsDTO;

    @NotNull
    private Collection<SpecializationDTO> specializationsDTO;

    @NotNull
    private Collection<UserSkillsDTO> skillsDTO;
}
