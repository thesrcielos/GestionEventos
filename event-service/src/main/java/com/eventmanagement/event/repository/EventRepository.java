package com.eventmanagement.event.repository;

import com.eventmanagement.event.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event,String> {
}
