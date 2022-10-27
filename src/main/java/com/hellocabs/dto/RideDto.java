/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
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
@Validated
public class RideDto {

    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rideTime;

    private double price;

    @Digits(integer = 10, fraction = 0)
    private long passengerMobileNumber;
    private double rating;
    private String rideStatus;

    @NotBlank(message = "Please Enter pickup location")
    private LocationDto pickupLocation;

    @NotBlank(message = "Please Enter drop location")
    private LocationDto  dropLocation;

}

