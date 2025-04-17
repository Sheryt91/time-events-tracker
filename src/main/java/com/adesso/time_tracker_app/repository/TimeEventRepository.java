package com.adesso.time_tracker_app.repository;

import com.adesso.time_tracker_app.entity.TimeEvent;
import com.adesso.time_tracker_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeEventRepository extends JpaRepository<TimeEvent,Long> {
    List<TimeEvent> findAllByUser(User user);
}
