package com.adesso.time_tracker_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeEventDTO {
    private int id;
    private LocalDate logDate;
    private BigDecimal hoursLogged;
    private String description;
    private String username;
}
