package com.dimitar.microservices.eventmanagement.eventmanagementapi.repositories;

import com.dimitar.microservices.eventmanagement.eventmanagementapi.entities.Participant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ParticipantRepository extends PagingAndSortingRepository<Participant, Long> {
    Participant findByEmail(@Param("email") String email);
}
