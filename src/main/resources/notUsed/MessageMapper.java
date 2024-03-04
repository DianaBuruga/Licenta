/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.MessageDTO;
import com.ulbs.careerstartup.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source = "sender", target = "senderDTO")
    @Mapping(source = "receiver", target = "receiverDTO")
    MessageDTO messageToMessageDTO(Message message);

    @Mapping(source = "senderDTO", target = "sender")
    @Mapping(source = "receiverDTO", target = "receiver")
    Message messageDTOToMessage(MessageDTO messageDTO);
}*/
