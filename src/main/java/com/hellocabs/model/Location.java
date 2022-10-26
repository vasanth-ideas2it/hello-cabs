package com.hellocabs.model;

import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 * Location class has the getters and setters for
 * Location
 *</p>
 *
 * @author Divya
 *
 * @version 1.0 Oct-26-2022
 *
 */
@Data
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;

    @Column(name = "location_name")
    private String locationName;
}
