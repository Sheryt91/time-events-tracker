package com.adesso.time_tracker_app.repository;

import com.adesso.time_tracker_app.entity.TimeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeEventRepository extends JpaRepository<TimeEvent,Long> {
}
