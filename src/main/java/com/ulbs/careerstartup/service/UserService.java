package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.constant.Role;
import com.ulbs.careerstartup.dto.*;
import com.ulbs.careerstartup.entity.User;
import com.ulbs.careerstartup.mapper.UserMapper;
import com.ulbs.careerstartup.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;

import static com.ulbs.careerstartup.constant.Constants.ULBSIBIU_SUFFIX;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private static final UserMapper userMapper = UserMapper.INSTANCE;

    public UserDTO findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper.INSTANCE::userToUserDTO)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }

    public OidcUser findByEmailOrSaveUser(OidcUser oidcUser) {
        String email = oidcUser.getEmail();

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(User.builder()
                        .name(oidcUser.getFullName())
                        .email(email)
                        .phone(oidcUser.getPhoneNumber())
                        .role(setRoleByEmail(email))
                        .build()));

        return new DefaultOidcUser(Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())),
                oidcUser.getIdToken(), oidcUser.getUserInfo());
    }

    public @NotNull Collection<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDTO).toList();
    }

    @Transactional
    public void deleteUser(String email) {
        if (userRepository.existsByEmail(email)) {
            userRepository.deleteByEmail(email);
        } else {
            throw new EntityNotFoundException("User with email " + email + " not found");
        }
    }

    public UserDTO saveUser(UserDTO userDTO) {
        return UserMapper.INSTANCE.userToUserDTO(
                userRepository.save(UserMapper.INSTANCE.userDTOToUser(userDTO)));
    }

    private Role setRoleByEmail(String email) {
        return email.endsWith(ULBSIBIU_SUFFIX) ? Role.STUDENT : Role.COMPANY_REPRESENTATIVE;
    }

    public @NotNull Collection<BibliographyDTO> getUserBibliography(String email) {
        return findByEmail(email).getBibliographiesDTO();
    }

    public @NotNull Collection<SpecializationDTO> getUserSpecialization(String email) {
        return findByEmail(email).getSpecializationsDTO();
    }

    public @NotNull Collection<SpecializationDTO> getUserSkills(String email) {
        return findByEmail(email).getSpecializationsDTO();
    }

    public @NotNull Collection<LanguageDTO> getUserLanguages(String email) {
        return findByEmail(email).getLanguagesDTO();
    }

    public @NotNull Collection<EventDTO> getUserEventSubscribed() {
        return findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getEventSubscribersDTO();
    }

    public @NotNull Collection<EventDTO> getUserEventCreated(String email) {
        return findByEmail(email).getCreatedEventsDTO();
    }

    public @NotNull Collection<JobCandidatesDTO> getUserJobsCandidate(String email) {
        return findByEmail(email).getJobCandidatesDTO();
    }

    public @NotNull Collection<JobHistoryDTO> getUserJobsHistory(String email) {
        return findByEmail(email).getJobHistoriesDTO();
    }

    public @NotNull Collection<NotificationDTO> getUserNotifications() {
        return findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getNotificationsDTO();
    }

    public @NotNull Collection<MessageDTO> getUserSentMessages() {
        return findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getSentMessagesDTO();
    }

    public @NotNull Collection<MessageDTO> getUserReceivedMessages() {
        return findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getReceivedMessagesDTO();
    }

    public @NotNull Collection<ReferralDTO> getUserWrittenReferrals(String email) {
        return findByEmail(email).getWrittenReferralsDTO();
    }

    public @NotNull Collection<ReferralDTO> getUserReceivedReferrals(String email) {
        return findByEmail(email).getReceivedReferralsDTO();
    }
}
