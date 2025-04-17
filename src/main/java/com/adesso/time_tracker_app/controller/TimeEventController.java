package com.adesso.time_tracker_app.controller;

import com.adesso.time_tracker_app.dto.TimeEventDTO;
import com.adesso.time_tracker_app.entity.TimeEvent;
import com.adesso.time_tracker_app.service.TimeEventService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/api/time-events")
public class TimeEventController {

    Logger logger = Logger.getLogger("TimeEventController.class");
    TimeEventService timeEventService;

    TimeEventController(TimeEventService timeEventService) {
        this.timeEventService = timeEventService;
    }

    @GetMapping("")
    public List<TimeEvent> getAllEvents(){
        return timeEventService.getAllEvents();
    }

    @GetMapping("/my-events")
    public List<TimeEvent> getEventsForCurrentUser() {
        logger.info("Inside myevents");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            logger.info("User is not authenticated.");
        } else {
            logger.info("Logged in as: " + auth.getName());
            return timeEventService.getEventsByUsername(auth.getName());
        }
        return null;

    }


    @PostMapping("")
    public TimeEvent createTimeEvent(@RequestBody TimeEventDTO timeEventDto){
        return timeEventService.createTimeEvent(timeEventDto);
    }
    @PutMapping("/{id}")
    public TimeEvent updateTimeEvent(@PathVariable Long id, @RequestBody TimeEventDTO updatedTimeEventDto) {
        return timeEventService.updateTimeEvent(id, updatedTimeEventDto);
    }


    @DeleteMapping("/{id}" )
    public void deleteEvent(@PathVariable long  id) {
        timeEventService.deleteEvent(id);
    }
}
