package com.ulbs.careerstartup.security;

import com.ulbs.careerstartup.constant.FileType;
import com.ulbs.careerstartup.constant.Role;
import com.ulbs.careerstartup.dto.CompanyDTO;
import com.ulbs.careerstartup.dto.JobHistoryDTO;
import com.ulbs.careerstartup.dto.UserDTO;
import com.ulbs.careerstartup.dto.UserInfo;
import com.ulbs.careerstartup.entity.Company;
import com.ulbs.careerstartup.entity.JobHistory;
import com.ulbs.careerstartup.entity.User;
import com.ulbs.careerstartup.entity.pk.FilePK;
import com.ulbs.careerstartup.service.CompanyService;
import com.ulbs.careerstartup.service.FileService;
import com.ulbs.careerstartup.service.JobHistoryService;
import com.ulbs.careerstartup.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.GMAIL_SUFFIX;
import static com.ulbs.careerstartup.constant.ExceptionMessages.INVALID_WORKSPACE_EMAIL_MESSAGE;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationService {
    private UserService userService;

    private JobHistoryService jobHistoryService;
    private FileService fileService;

    public UserDTO saveUserIfDoesNotExist(UserInfo userInfo) {
        UserDTO user;
        checkIfEmailIsWorkspaceEmail(userInfo);
        try {
            user = userService.findByEmail(userInfo.email());
        } catch (Exception e) {
            user = createAndStoreNewUser(userInfo);
            storeUserProfileImage(userInfo, user.getId());
        }
        return user;
    }

    @Transactional
    private UserDTO createAndStoreNewUser(UserInfo userInfo) {
        UserDTO userDTO = userService.saveUserAndSetRole(userInfoToUserDTO(userInfo));
        if(userDTO.getRole().equals(Role.COMPANY_REPRESENTATIVE)){
            CompanyDTO company = CompanyDTO.builder().website("https://"+userInfo.email().split("@")[1]).build();
            JobHistoryDTO jobHistoryDTO = JobHistoryDTO.builder()
                    .position("HR")
                    .needQualification(true)
                    .companyDTO(company)
                    .userDTO(UserDTO.builder().id(userDTO.getId()).build()).build();
            jobHistoryService.saveJobHistory(jobHistoryDTO);
        }
        return userDTO;
    }

    private UserDTO userInfoToUserDTO(UserInfo userInfo) {
        return UserDTO.builder()
                .name(userInfo.name())
                .email(userInfo.email())
                .phone(userInfo.phone())
                .build();
    }

    private void storeUserProfileImage(UserInfo userInfo, UUID userId) {
        FilePK filePK = FilePK.builder()
                .tableId(userId)
                .tableName("user")
                .type(FileType.PROFILE_PHOTO)
                .build();
        try {
            fileService.storeImageFromUrlOrStoreDefault(filePK, userInfo.picture());
        } catch (Exception e) {
            log.error("Failed to store user profile image for user: " + userId, e);
        }
    }

    public void checkIfEmailIsWorkspaceEmail(UserInfo userInfo) throws OAuth2AuthenticationException {
        String email = userInfo.email();
        if (email == null || email.contains(GMAIL_SUFFIX)) {
            throw new OAuth2AuthenticationException(INVALID_WORKSPACE_EMAIL_MESSAGE);
        }
    }
}
