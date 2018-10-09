package com.dimitar.microservices.eventmanagement.eventmanagementapi.controllers;

import com.dimitar.microservices.eventmanagement.eventmanagementapi.entities.Participant;
import com.dimitar.microservices.eventmanagement.eventmanagementapi.exceptions.AlreadyCheckedInException;
import com.dimitar.microservices.eventmanagement.eventmanagementapi.exceptions.NotCheckedInException;
import com.dimitar.microservices.eventmanagement.eventmanagementapi.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RepositoryRestController
@RequestMapping("/events")
public class CheckInController {

    @Autowired
    private ParticipantRepository participantRepository;

    @PostMapping("/checkin/{id}")
    public ResponseEntity<PersistentEntityResource> checkIn(@PathVariable Long id, PersistentEntityResourceAssembler assembler) {
        Optional<Participant> optional = participantRepository.findById(id);

        if (!optional.isPresent()) {
            throw new ResourceNotFoundException();
        }

        if (optional.get().isCheckedIn()) {
            throw new AlreadyCheckedInException();
        }

        optional.get().setCheckedIn(true);
        participantRepository.save(optional.get());

        return ResponseEntity.ok(assembler.toResource(optional.get()));
    }

    @PostMapping("/checkout/{id}")
    public ResponseEntity<PersistentEntityResource> checkOut(@PathVariable Long id, PersistentEntityResourceAssembler assembler) {
        Optional<Participant> optional = participantRepository.findById(id);

        if (!optional.isPresent()) {
            throw new ResourceNotFoundException();
        }

        if (!optional.get().isCheckedIn()) {
            throw new NotCheckedInException();
        }

        optional.get().setCheckedIn(false);
        participantRepository.save(optional.get());

        return ResponseEntity.ok(assembler.toResource(optional.get()));
    }
}
