/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * <p>
 *   A Dto object which is responsible for collect and display
 *   all possible information regarding to a ride from user through
 *   controller and further convert to Entity object through
 *   service using mapper classes
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
@Data
@Validated
public class RideDto {

    private int id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideBookedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ridePickedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideDroppedTime;
    private double price;

    @Digits(integer = 10, fraction = 0)
    private long passengerMobileNumber;

    private double rating;

    private String rideStatus;

    private boolean isCancelled;

    private LocationDto pickupLocation;

    private LocationDto  dropLocation;

    public void setIsCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public boolean getIsCancelled() {
        return isCancelled;
    }
}

