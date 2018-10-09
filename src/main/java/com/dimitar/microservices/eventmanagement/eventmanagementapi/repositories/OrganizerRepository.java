package com.dimitar.microservices.eventmanagement.eventmanagementapi.repositories;

import com.dimitar.microservices.eventmanagement.eventmanagementapi.entities.Event;
import com.dimitar.microservices.eventmanagement.eventmanagementapi.entities.Organizer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizerRepository extends PagingAndSortingRepository<Organizer, Long> {

    List<Event> findByName(@Param("name") String name);
}
