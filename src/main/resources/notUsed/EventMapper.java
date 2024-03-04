/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.EventDTO;
import com.ulbs.careerstartup.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "creator", target = "creatorDTO")
    @Mapping(source = "subscribers", target = "subscribersDTO")
    EventDTO eventToEventDTO(Event event);

    @Mapping(source = "creatorDTO", target = "creator")
    @Mapping(target = "subscribers", ignore = true)
    Event eventDTOToEvent(EventDTO eventDTO);
}
*/
