package com.adesso.time_tracker_app.controller;

import com.adesso.time_tracker_app.dto.TimeEventDTO;
import com.adesso.time_tracker_app.entity.TimeEvent;
import com.adesso.time_tracker_app.repository.TimeEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/time-events")
public class TimeEventController {

    @Autowired
    TimeEventRepository timeEventRepository;

    @GetMapping("")
    public List<TimeEvent> getAllEvents(){
        return timeEventRepository.findAll();
    }

    @PostMapping("")
    public TimeEvent createTimeEvent(@RequestBody TimeEventDTO timeEventDto){
        TimeEvent timeEventEntity= new TimeEvent();
        timeEventEntity.setDescription(timeEventDto.getDescription());
        timeEventEntity.setHoursLogged(timeEventDto.getHoursLogged());
        timeEventEntity.setLogDate(timeEventDto.getLogDate());
        return timeEventRepository.save(timeEventEntity);
    }

    @DeleteMapping("/{id}" )
    public void deleteEvent(@PathVariable long  id) {
      Optional<TimeEvent> eventToBeDeleted= Optional.of(new TimeEvent());
      eventToBeDeleted=timeEventRepository.findById(id);
      if(eventToBeDeleted.isPresent()){
          timeEventRepository.deleteById(id);
      }
    }
}
