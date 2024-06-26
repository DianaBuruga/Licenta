package com.ulbs.careerstartup.service;

import com.lowagie.text.DocumentException;
import com.ulbs.careerstartup.constant.Role;
import com.ulbs.careerstartup.dto.FileDTO;
import com.ulbs.careerstartup.dto.UserDTO;
import com.ulbs.careerstartup.entity.User;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.UserRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import com.ulbs.careerstartup.util.CVGenerator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotNull;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.ULBSIBIU_SUFFIX;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private CVGenerator cvGenerator;
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
    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }


    public UserDTO saveUser(UserDTO userDTO) {
        return mapper.userToUserDTO(userRepository.save(mapper.userDTOToUser(userDTO)));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User with email " + userDTO.getEmail() + " not found"));
        if (userDTO.getId() != null) {
            user.setId(userDTO.getId());
        }
        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getPhone() != null) {
            user.setPhone(userDTO.getPhone());
        }
        if (userDTO.getWebsite() != null) {
            user.setWebsite(userDTO.getWebsite());
        }
        if (userDTO.getDescription() != null) {
            user.setDescription(userDTO.getDescription());
        }
        if (userDTO.getStatus() != null) {
            user.setStatus(userDTO.getStatus());
        }
        return mapper.userToUserDTO(userRepository.save(user));
    }

    private Role getRoleByEmail(String email) {
        return email.endsWith(ULBSIBIU_SUFFIX) ? Role.STUDENT : Role.COMPANY_REPRESENTATIVE;
    }

    public UserDTO findById(UUID id) {
        return userRepository.findById(id)
                .map(mapper::userToUserDTO)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public Collection<UserDTO> findByCriteria(List<SearchCriteria> criteria) {
        return userRepository
                .findAll(new GenericSpecification<>(criteria))
                .stream()
                .map(mapper::userToUserDTO)
                .toList();
    }

    public FileDTO generateCV(UUID id) throws DocumentException, IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        return mapper.fileToFileDTO(cvGenerator.generateCV(user));
    }

    public boolean isAutenticatedUser(String email, Principal principal) {
        return email.equals(principal.getName());
    }
}
