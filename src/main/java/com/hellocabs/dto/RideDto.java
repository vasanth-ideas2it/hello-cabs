/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hellocabs.model.Location;
import lombok.Data;

import javax.xml.stream.Location;
import java.time.LocalDateTime;

/**
 * Contains all possible information that are related
 * to ride of a customer
 *
 * This file is created on 20/10/2022
 * @author : Pradeep
 *
 */
@Data
public class RideDto {

    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideTime;
    private double price;
    private long passengerMobileNumber;
    private double rating;
    private String rideStatus;
    private Location pickupLocation;
    private Location  dropLocation;

    public String toString() {
        return "" + price;
    }
}

