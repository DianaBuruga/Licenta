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
@EqualsAndHashCode
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

    private UserStatus status;

    private FileDTO profilePhoto;

    private Collection<BibliographyDTO> bibliographiesDTO;

    private Collection<EventDTO> createdEventsDTO;

    private Collection<EventDTO> eventSubscribersDTO;

    private Collection<ExperienceDTO> experiencesDTO;

    private Collection<JobCandidatesDTO> jobCandidatesDTO;

    private Collection<JobHistoryDTO> jobHistoriesDTO;

    private Collection<LanguageDTO> languagesDTO;

    private Collection<MessageDTO> sentMessagesDTO;

    private Collection<MessageDTO> receivedMessagesDTO;

    private Collection<NotificationDTO> notificationsDTO;

    private Collection<ReferralDTO> writtenReferralsDTO;

    private Collection<ReferralDTO> receivedReferralsDTO;

    private Collection<SpecializationDTO> specializationsDTO;

    private Collection<UserSkillsDTO> skillsDTO;
}
