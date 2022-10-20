package com.hellocabs.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cabCategory")
public class CabCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Column(name = "id")
    private int id;

    @Column(name = "cabType")
    private String cabType;

    @Column(name = "initialFare")
    private double initialFare;

    @Column(name = "extraKmFare")
    private double extraKmFare;

    @Column(name = "additionalFare")
    private double additionalFare;
}
