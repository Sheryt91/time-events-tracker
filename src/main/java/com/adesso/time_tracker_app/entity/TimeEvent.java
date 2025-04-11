package com.adesso.time_tracker_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "time-event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "log-date")
   // private Date logDate;
    private LocalDate logDate;

    @Column(name = "hours-logged")
    private int hoursLogged;

    @Column(name = "description")
    private String description;


}
