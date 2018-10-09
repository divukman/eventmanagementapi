package com.dimitar.microservices.eventmanagement.eventmanagementapi.entities.projections;

import com.dimitar.microservices.eventmanagement.eventmanagementapi.entities.Venue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Virtual as in something that does not exist as a data itself. It's constructed.
 */
@Projection(name="virtual", types = {Venue.class})
public interface StreetAddressProjection {

    @Value("#{target.streetAddress} #{target.streetAddress2}")
    String getCompleteStreetAddress();
}
