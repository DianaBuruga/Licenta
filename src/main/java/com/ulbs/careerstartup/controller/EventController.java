package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.EventApiDoc;
import com.ulbs.careerstartup.dto.EventDTO;
import com.ulbs.careerstartup.service.EventService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Collection<EventDTO>> findAllEvents() {
        return ResponseEntity.ok(eventService.findAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<EventDTO>> findEventsByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(eventService.findEventsByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<EventDTO> saveEvent(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.saveEvent(eventDTO));
    }

    @PatchMapping
    public ResponseEntity<EventDTO> updateEvent(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.updateEvent(eventDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@RequestBody EventDTO eventDTO) {
        eventService.deleteEvent(eventDTO);
        return ResponseEntity.noContent().build();
    }
}
