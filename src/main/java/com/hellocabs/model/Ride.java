/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import java.time.LocalDateTime;

/**
 * <p>
 *   Entity object which contains all possible information that
 *   are related to a ride and used to connect with database
 *   through service
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
@Entity
@Data
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideBookedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ridePickedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideDroppedTime;

    private double price;

    private long passengerMobileNumber;

    private double rating;

    private String rideStatus;

    private boolean isCancelled;

    @OneToOne(targetEntity = Location.class)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "pickup_location")
    private Location pickupLocation;

    @OneToOne(targetEntity = Location.class)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "drop_location")
    private  Location dropLocation;

    public void setIsCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public boolean getIsCancelled() {
        return isCancelled;
    }
}
