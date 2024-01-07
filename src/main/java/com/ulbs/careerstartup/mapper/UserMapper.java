package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.UserDTO;
import com.ulbs.careerstartup.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
