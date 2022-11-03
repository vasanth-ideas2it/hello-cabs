/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Set;

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
 * Class is used to define the cab details and their types by using
 * Entity object with possible information and connect to database
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
    private Integer id;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "cab_number")
    private String cabNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "driver_rating")
    private Double driverRating;

    @Column(name = "cab_status")
    private String cabStatus;

    @OneToMany(mappedBy = "cab") //cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@LazyCollection(LazyCollectionOption.FALSE)
    //@JoinColumn(name = "cab_id")
    private Set<Ride> rides;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "current_location")
    private String test;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "cab_category_id")
    private Integer cabCategoryId ;
}
