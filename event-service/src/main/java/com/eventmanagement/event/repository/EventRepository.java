package com.eventmanagement.event.repository;

import com.eventmanagement.event.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event,String> {
    public boolean existsById(String id);
}
