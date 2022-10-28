/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.controller;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.dto.CabDto;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.dto.RideDto;
import com.hellocabs.dto.StatusDto;
import com.hellocabs.logger.LoggerConfiguration;
import com.hellocabs.model.CabCategory;
import com.hellocabs.service.RideService;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 *   Implemented this class whenever a customer books a ride,
 *   to fetch particular ride, to show all ride history,
 *   to delete a ride while booking is not complete etc.,
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
@RestController
@RequestMapping("ride")
public class RideController {

    private final RideService rideService;
    private final Logger logger = LoggerConfiguration
            .getInstance("RideController.class");

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    /**
     * <p>
     *   Used this method to fetch a particular ride
     *   by passing ride id to service
     * </p>
     *
     * @param id {@link int} ride's id to be searched
     * @return {@link RideDto} searched ride object
     *
     */
    @GetMapping("search/{id}")
    public RideDto searchRideById(@PathVariable int id) {
        logger.info("id " + id);
        return rideService.searchRideById(id);
    }

    /**
     * <p>
     *   Used this method to retrieve all rides
     *   irrespective of id
     * </p>
     *
     * @return {@link Set<RideDto>} set of all rides
     *
     */
    public Set<RideDto> retrieveRides() {
        return rideService.retrieveRides();
    }

    /**
     * <p>
     *   Used this method to update particular ride
     *   by inserting the values of fields and id
     *   to be updated
     * </p>
     *
     * @param rideDto {@link RideDto} ride details to be updated
     * @return {@link String} updated ride
     *
     */
    @PutMapping("update")
    public String updateRide(@RequestBody @Valid RideDto rideDto) {

        if (null != rideService.updateRide(rideDto)) {
            return "Ride " + rideDto.getId() + " Updated Successfully";
        }
        return "Couldn't update ride";
    }

    /**
     * <p>
     *   Used this method whenever a customer books a ride
     *   and choose category by using category id and also
     *   ride details
     * </p>
     *
     * @param rideDto {@link RideDto} ride details of a customer
     * @return cabDtos {@link Set<CabDto>} list of cab that are
     *              available on particular location
     *
     */
    @PostMapping("book/{categoryId}")
    public String bookRide(@RequestBody @Valid RideDto rideDto,
                           @PathVariable int categoryId) {
        return rideService.bookRide(rideDto, categoryId);
    }

    /**
     * <p>
     *   This method is often used to change the status of both ride and cab
     *   by passing the user input as an object
     * </p>
     *
     * @param statusDto {@link StatusDto} contains information
     *                          about ride status
     * @return {@link CabDto} assigned cab details
     *
     */
    @PutMapping("status")
    public CabDto updateStatus(@RequestBody @Valid StatusDto statusDto) {
        return rideService.updateStatus(statusDto);
    }

    /**
     * <p>
     *   This method is used to confirm the ride is booked
     *   if not accepted by cab for long time, ride will be
     *   cancelled automatically and shows user a message
     *   to choose another cab category
     * </p>
     *
     * @param rideId {@link int}
     * @return rideDto {@link RideDto} updated rideDto
     *
     */
    @PostMapping("waiting/{rideId}")
    public String confirmRide(@PathVariable int rideId) {
        return rideService.confirmRide(rideId);
    }

    /**
     * <p>
     *   Used this method whenever a user wants to cancel
     *   the ride for some reason
     * </p>
     *
     * @param id {@link int} ride details to be updated
     * @return {@link String} reason for ride cancellation
     *
     */
    @DeleteMapping("cancel/{id}")
    public String deleteRideById(@PathVariable int id){
        boolean isDeleted = rideService.deleteRideById(id);
        return isDeleted
                ? "Ride " + id + " Canceled Successfully"
                : "Ride " + id + " couldn't cancel selected ride";
    }
}
