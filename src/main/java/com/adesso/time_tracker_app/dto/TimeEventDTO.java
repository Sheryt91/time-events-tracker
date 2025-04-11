package com.adesso.time_tracker_app.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeEventDTO {
    private int id;

//    private Date logDate;
    private LocalDate logDate;
    private int hoursLogged;

    private String description;
}
