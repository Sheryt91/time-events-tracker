package com.adesso.time_tracker_app.service;

import com.adesso.time_tracker_app.dto.TimeEventDTO;
import com.adesso.time_tracker_app.entity.TimeEvent;

import java.util.List;


public interface TimeEventService {

    List<TimeEvent> getAllEvents();

    TimeEvent createTimeEvent(TimeEventDTO timeEventDto);

    TimeEvent updateTimeEvent(Long id, TimeEventDTO updatedTimeEventDto);

    void deleteEvent(long id);

    List<TimeEvent> getEventsByUsername(String username);
}
