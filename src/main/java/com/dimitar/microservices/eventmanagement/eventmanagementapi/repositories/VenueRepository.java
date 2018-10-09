package com.dimitar.microservices.eventmanagement.eventmanagementapi.repositories;

import com.dimitar.microservices.eventmanagement.eventmanagementapi.entities.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface VenueRepository extends PagingAndSortingRepository<Venue, Long> {

    Page<Venue> findByPostalCode(@Param("postalCode") String postalCode, Pageable page);
}
