package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.constant.Role;
import com.ulbs.careerstartup.dto.*;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.UserRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.ULBSIBIU_SUFFIX;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private Mapper mapper;

    public UserDTO findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(mapper::userToUserDTO)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }

    public UserDTO saveUserAndSetRole(UserDTO userDTO) {
        userDTO.setRole(getRoleByEmail(userDTO.getEmail()));
        return mapper.userToUserDTO(userRepository.save(mapper.userDTOToUser(userDTO)));
    }

    public @NotNull Collection<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(mapper::userToUserDTO).toList();
    }

    @Transactional
    public void deleteUser(UserDTO userDTO) {
        userRepository.delete(mapper.userDTOToUser(userDTO));
    }

    public UserDTO saveUser(UserDTO userDTO) {
        return mapper.userToUserDTO(
                userRepository.save(mapper.userDTOToUser(userDTO)));
    }

    private Role getRoleByEmail(String email) {
        return email.endsWith(ULBSIBIU_SUFFIX) ? Role.STUDENT : Role.COMPANY_REPRESENTATIVE;
    }

    public @NotNull Collection<BibliographyDTO> findUserBibliography(String email) {
        return findByEmail(email).getBibliographiesDTO();
    }

    public @NotNull Collection<SpecializationDTO> findUserSpecialization(String email) {
        return findByEmail(email).getSpecializationsDTO();
    }

    public @NotNull Collection<SpecializationDTO> findUserSkills(String email) {
        return findByEmail(email).getSpecializationsDTO();
    }

    public @NotNull Collection<LanguageDTO> findUserLanguages(String email) {
        return findByEmail(email).getLanguagesDTO();
    }

    public @NotNull Collection<EventDTO> findUserEventSubscribed() {
        return findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getEventSubscribersDTO();
    }

    public @NotNull Collection<EventDTO> findUserEventCreated(String email) {
        return findByEmail(email).getCreatedEventsDTO();
    }

    public @NotNull Collection<JobCandidatesDTO> findUserJobsCandidate(String email) {
        return findByEmail(email).getJobCandidatesDTO();
    }

    public @NotNull Collection<JobHistoryDTO> findUserJobsHistory(String email) {
        return findByEmail(email).getJobHistoriesDTO();
    }

    public @NotNull Collection<NotificationDTO> findUserNotifications() {
        return findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getNotificationsDTO();
    }

    public @NotNull Collection<MessageDTO> findUserSentMessages() {
        return findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getSentMessagesDTO();
    }

    public @NotNull Collection<MessageDTO> findUserReceivedMessages() {
        return findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getReceivedMessagesDTO();
    }

    public @NotNull Collection<ReferralDTO> findUserWrittenReferrals(String email) {
        return findByEmail(email).getWrittenReferralsDTO();
    }

    public @NotNull Collection<ReferralDTO> findUserReceivedReferrals(String email) {
        return findByEmail(email).getReceivedReferralsDTO();
    }

    public UserDTO findById(UUID id) {
        return userRepository.findById(id)
                .map(mapper::userToUserDTO)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public Collection<UserDTO> findUsersByCriteria(List<SearchCriteria> criteria) {
        return userRepository
                .findAll(new GenericSpecification<>(criteria))
                .stream()
                .map(mapper::userToUserDTO)
                .toList();
    }

    public UserDTO updateUser(UserDTO userDTO) {
        return mapper.userToUserDTO(userRepository.save(mapper.userDTOToUser(userDTO)));
    }
}
