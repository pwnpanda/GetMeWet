package com.example.getmewet.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private  final String name;
    private final String picture;
    private String note;

    public Plant(String name, String picture){
        this.name = name;
        this.picture = picture;
        note = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getNote() {
        return note;
    }

    public Plant getPlant(){
        return this;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    public void setNote(String note) {
        this.note = note;
    }
}
