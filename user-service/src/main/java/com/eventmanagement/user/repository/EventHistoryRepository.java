package com.eventmanagement.user.repository;

import com.eventmanagement.user.model.EventHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventHistoryRepository extends JpaRepository<EventHistory,Long> {
}
