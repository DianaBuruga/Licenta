package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.EventDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.EventRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EventService {

    private EventRepository eventRepository;

    private Mapper mapper;

    public @NotNull Collection<EventDTO> findAllEvents() {
        return eventRepository.findAll().stream().map(mapper::eventToEventDTO).toList();
    }

    public EventDTO findById(UUID id) {
        return eventRepository.findById(id)
                .map(mapper::eventToEventDTO)
                .orElseThrow(() -> new EntityNotFoundException("Event with id " + id + " not found"));
    }

    public Collection<EventDTO> findEventsByCriteria(List<SearchCriteria> searchCriteria) {
        return eventRepository
                .findAll(new GenericSpecification<>(searchCriteria), PageRequest.of(0, 10))
                .map(mapper::eventToEventDTO)
                .toList();
    }

    public EventDTO saveEvent(EventDTO eventDTO) {
        return mapper.eventToEventDTO(eventRepository.save(mapper.eventDTOToEvent(eventDTO)));
    }

    public EventDTO updateEvent(EventDTO eventDTO) {
        if (eventRepository.existsById(eventDTO.getId()))
            return mapper.eventToEventDTO(eventRepository.save(mapper.eventDTOToEvent(eventDTO)));
        else
            throw new EntityNotFoundException("Event with id " + eventDTO.getId() + " not found");
    }

    public void deleteEvent(EventDTO eventDTO) {
        eventRepository.delete(mapper.eventDTOToEvent(eventDTO));
    }

}
