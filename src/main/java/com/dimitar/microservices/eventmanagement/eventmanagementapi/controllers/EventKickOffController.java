package com.dimitar.microservices.eventmanagement.eventmanagementapi.controllers;

import com.dimitar.microservices.eventmanagement.eventmanagementapi.entities.Event;
import com.dimitar.microservices.eventmanagement.eventmanagementapi.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RepositoryRestController
@RequestMapping("/events")
public class EventKickOffController {

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/start/{id}")
    public ResponseEntity start(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);

        if (!event.isPresent()) {
            throw new ResourceNotFoundException("Event with id: " + id + " was not found!");
        }

        event.get().setStarted(true);
        eventRepository.save(event.get());

        return ResponseEntity.ok(event.get().getName() + " has started!");
    }
}
