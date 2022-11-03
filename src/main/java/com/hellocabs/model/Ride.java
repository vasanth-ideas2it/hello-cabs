/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Getter
@Setter
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideBookedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ridePickedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideDroppedTime;

    private Double price;

    private Long passengerMobileNumber;

    private Double rating;

    private String rideStatus;

    private String feedback;

    private boolean isCancelled;

    @ManyToOne(targetEntity = Location.class)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "pickup_location")
    private Location pickupLocation;

    @OneToOne(targetEntity = Location.class)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "drop_location")
    private  Location dropLocation;

    @ManyToOne
    @JoinColumn(name = "cab_id")
    private Cab cab;

    @ManyToOne(targetEntity = Customer.class)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void setIsCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public boolean getIsCancelled() {
        return isCancelled;
    }
}
