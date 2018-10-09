package com.dimitar.microservices.eventmanagement.eventmanagementapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@JsonPropertyOrder({"resoureceId"})
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    protected Instant created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public long getResourceId() {
        return id;
    }
}
