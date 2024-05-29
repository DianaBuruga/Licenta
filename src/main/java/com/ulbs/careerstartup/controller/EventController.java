package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.EventApiDoc;
import com.ulbs.careerstartup.dto.EventDTO;
import com.ulbs.careerstartup.service.EventService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.BY_CRITERIA;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Event", description = "The Event API")
public class EventController implements EventApiDoc {

    private EventService eventService;

    @GetMapping
    public Collection<EventDTO> findAllEvents() {
        return eventService.findAllEvents();
    }

    @GetMapping("/{id}")
    public EventDTO findEventById(@PathVariable UUID id) {
        return eventService.findEventById(id);
    }

    @PostMapping(BY_CRITERIA)
    public Collection<EventDTO> findByCriteria(@RequestBody List<SearchCriteria> criteria) {
        return eventService.findByCriteria(criteria);
    }

    @PostMapping
    public EventDTO saveEvent(@RequestBody EventDTO eventDTO) {
        return eventService.saveEvent(eventDTO);
    }

    @PatchMapping
    public EventDTO updateEvent(@RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(eventDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@RequestBody EventDTO eventDTO) {
        eventService.deleteEvent(eventDTO);
    }
}
