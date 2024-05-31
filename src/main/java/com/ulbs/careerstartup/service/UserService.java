package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.constant.Role;
import com.ulbs.careerstartup.dto.UserDTO;
import com.ulbs.careerstartup.entity.User;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.UserRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotNull;
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
        return mapper.userToUserDTO(userRepository.save(mapper.userDTOToUser(userDTO)));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User with email " + userDTO.getEmail() + " not found"));
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setWebsite(userDTO.getWebsite());
        user.setDescription(userDTO.getDescription());
        user.setStatus(userDTO.getStatus());
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
}
