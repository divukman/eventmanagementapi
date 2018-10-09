package com.dimitar.microservices.eventmanagement.eventmanagementapi.repositories;

import com.dimitar.microservices.eventmanagement.eventmanagementapi.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.ZoneId;
import java.util.List;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    Page<Event> findByName(@Param("name") String name, Pageable pageable);
    Page<Event> findByNameAndZoneId(@Param("name") String name, @Param("zoneId") ZoneId zoneId, Pageable pageable);

}
