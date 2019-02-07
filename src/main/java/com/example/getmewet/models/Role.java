package com.example.getmewet.models;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @Column(name = "role_id")
    private int id;
    @Column(name = "role")
    private String role;
}
