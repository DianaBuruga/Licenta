package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ulbs.careerstartup.constant.Constants;
import com.ulbs.careerstartup.constant.Role;
import com.ulbs.careerstartup.constant.UserStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = INVALID_EMAIL)
    private String email;

    @NotNull(message = "Role is required")
    private Role role;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Phone is required")
    @NotEmpty(message = "Phone is required")
    @Pattern(regexp = Constants.PHONE_REGEX, message = INVALID_PHONE)
    private String phone;

    @NotNull(message = "Website is required")
    @NotEmpty(message = "Website is required")
    @URL(message = "Invalid URL")
    private String website;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    private String description;

    private UserStatus status;

    private FileDTO profilePhoto;

    @JsonProperty("bibliographies")
    private Collection<BibliographyDTO> bibliographiesDTO;

    @JsonProperty("experiences")
    private Collection<ExperienceDTO> experiencesDTO;

    @JsonProperty("postedJobs")
    private Collection<JobHistoryDTO> jobHistoriesDTO;

    @JsonProperty("languages")
    private Collection<LanguageDTO> languagesDTO;

    @JsonProperty("receivedReferrals")
    private Collection<ReferralDTO> receivedReferralsDTO;

    @JsonProperty("specializations")
    private Collection<SpecializationDTO> specializationsDTO;

    @JsonProperty("skills")
    private Collection<UserSkillsDTO> skillsDTO;
}
