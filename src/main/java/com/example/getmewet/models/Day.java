package com.example.getmewet.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private final Date date;

    public Day(Date date){
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getWeekDay() {
        return date.toLocalDate().getDayOfWeek().toString();
    }

    public int getDayofMonth() {
        return date.toLocalDate().getDayOfMonth();
    }

    public int getMonth() {
        return date.toLocalDate().getMonthValue();
    }

    public int getYear() {
        return date.toLocalDate().getYear();
    }
}
