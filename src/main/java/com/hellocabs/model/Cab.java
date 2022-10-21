package com.hellocabs.model;


import lombok.Data;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <h> Cab </h>
 * <p>
 * Class is used to define the cab details and their types
 * </p>
 *
 * @version 1.0
 * @author Jaganathan R
 */
@Data
@Entity
@Table(name = "cab")
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "cab_number")
    private String cabNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobile_number")
    private long mobileNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "driver_rating")
    private double driverRating;

    @Column(name = "cab_status")
    private String cabStatus;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cab_id")
    private List<Ride> rides;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "current_location")
    private String currentLocation;


}
