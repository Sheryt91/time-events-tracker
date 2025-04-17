package com.adesso.time_tracker_app.service.impl;

import com.adesso.time_tracker_app.dto.TimeEventDTO;
import com.adesso.time_tracker_app.entity.TimeEvent;
import com.adesso.time_tracker_app.entity.User;
import com.adesso.time_tracker_app.repository.TimeEventRepository;
import com.adesso.time_tracker_app.repository.UserRepository;
import com.adesso.time_tracker_app.service.TimeEventService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeEventServiceImpl implements TimeEventService {

    TimeEventRepository timeEventRepository;
    UserRepository userRepository;
    TimeEventServiceImpl(TimeEventRepository repository, UserRepository userRepository) {
        this.timeEventRepository = repository;
        this.userRepository=userRepository;
    }

    @Override
    public List<TimeEvent> getAllEvents() {
        return timeEventRepository.findAll();
    }

    @Override
    public TimeEvent createTimeEvent(TimeEventDTO timeEventDto) {
        TimeEvent timeEventEntity = new TimeEvent();
        timeEventEntity.setDescription(timeEventDto.getDescription());
        timeEventEntity.setHoursLogged(timeEventDto.getHoursLogged());
        timeEventEntity.setLogDate(timeEventDto.getLogDate());
        return timeEventRepository.save(timeEventEntity);
    }

    @Override
    public TimeEvent updateTimeEvent(Long id, TimeEventDTO updatedTimeEventDto) {
        TimeEvent timeEventEntity = timeEventRepository.findById(id).orElseThrow();

        timeEventEntity.setDescription(updatedTimeEventDto.getDescription());
        timeEventEntity.setHoursLogged(updatedTimeEventDto.getHoursLogged());
        timeEventEntity.setLogDate(updatedTimeEventDto.getLogDate());
        return timeEventRepository.save(timeEventEntity);
    }

    @Override
    public void deleteEvent(long id) {
        Optional<TimeEvent> eventToBeDeleted = timeEventRepository.findById(id);
        if (eventToBeDeleted.isPresent()) {
            timeEventRepository.deleteById(id);
        }
    }

    @Override
    public List<TimeEvent> getEventsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return timeEventRepository.findAllByUser(user);    }
}
