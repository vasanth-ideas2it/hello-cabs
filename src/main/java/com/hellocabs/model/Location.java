package com.hellocabs.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cabCategory")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Column(name = "id")
    private int id;

    @Column(name = "locationName")
    private String locationName;
}
