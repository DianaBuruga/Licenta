package com.ulbs.careerstartup.dto.cv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ulbs.careerstartup.constant.Constants;
import com.ulbs.careerstartup.constant.Role;
import com.ulbs.careerstartup.constant.UserStatus;
import com.ulbs.careerstartup.dto.*;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Collection;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.INVALID_EMAIL;
import static com.ulbs.careerstartup.constant.ValidationMessages.INVALID_PHONE;

public class UserCVDTO {

    private UUID id;

    @NotNull
    @Email(message = INVALID_EMAIL)
    private String email;

    @NotNull
    private Role role;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = Constants.PHONE_REGEX, message = INVALID_PHONE)
    private String phone;

    @NotNull
    @URL
    private String website;

    @NotNull
    private String description;

    private UserStatus status;

    private FileDTO profilePhoto;

    @JsonProperty("experiences")
    private Collection<ExperienceDTO> experiencesDTO;

    @JsonProperty("languages")
    private Collection<LanguageDTO> languagesDTO;

    @JsonProperty("sentMessages")
    private Collection<MessageDTO> sentMessagesDTO;

    @JsonProperty("receivedMessages")
    private Collection<MessageDTO> receivedMessagesDTO;

    @JsonProperty("notifications")
    private Collection<NotificationDTO> notificationsDTO;

    @JsonProperty("receivedReferrals")
    private Collection<ReferralDTO> receivedReferralsDTO;

    @JsonProperty("specializations")
    private Collection<SpecializationDTO> specializationsDTO;

    @JsonProperty("skills")
    private Collection<UserSkillsDTO> skillsDTO;
}
