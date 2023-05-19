package org.umpires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.umpires.entity.EventDates;

public interface EventRepository extends JpaRepository<EventDates, Long> {
}
