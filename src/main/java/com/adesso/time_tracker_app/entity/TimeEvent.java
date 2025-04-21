package com.adesso.time_tracker_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "time_event")
@Getter
@Setter
@NoArgsConstructor
public class TimeEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "time_event_seq")
    @SequenceGenerator(name = "time_event_seq", sequenceName = "time_event_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "log_date")
    private LocalDate logDate;

    @Column(name = "hours_logged")
    private int hoursLogged;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
