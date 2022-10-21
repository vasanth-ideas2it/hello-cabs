/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * Contains all possible information that are related
 * to ride of a customer
 *
 * This file is created on 20/10/2022
 * @author : Pradeep
 *
 */
@Entity
@Data
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideTime;
    private double price;
    private long passengerMobileNumber;
    private double rating;
    private String rideStatus;
    @OneToOne(targetEntity = Location.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pickup_location")
    private Location pickupLocation;
    @OneToOne(targetEntity = Location.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "drop_location")
    private  Location dropLocation;

}
