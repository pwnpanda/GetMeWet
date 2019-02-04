package com.example.getmewet.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final Plant plant;
    private final Day day;
    private boolean isWet;

    public Status(Plant plant, Day day){
        this.day = day;
        this.plant = plant;
        this.isWet = false;
    }

    public Plant getPlant() {
        return plant;
    }

    public Day getDay() {
        return day;
    }

    public boolean isWet() {
        return isWet;
    }

    public void setWet(boolean wet) {
        this.isWet = wet;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
