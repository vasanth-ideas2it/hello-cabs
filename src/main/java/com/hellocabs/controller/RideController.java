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
import com.hellocabs.logger.LoggerConfiguration;
import com.hellocabs.service.RideService;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.Set;

/**
 *
 * Implemented to create a new ride or search, update, and delete
 * existing ride
 * This file is created on 20/10/2022
 * @author : Pradeep
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
     *
     * Search particular ride by using id
     *
     * @param id {@link int} ride details to be searched
     * @return {@link RideDto} ride details to be displayed
     *
     */
    @GetMapping("search/{id}")
    public RideDto searchRideById(@PathVariable int id) {
        return rideService.searchRideById(id);
    }

    /**
     *
     * retrieve all rides
     *
     * @return {@link Set<RideDto>} set of all rides
     *
     */
    public Set<RideDto> retrieveRides() {
        return rideService.retrieveRides();
    }

    /**
     *
     * Update particular ride by existing ride
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
     *
     * Shows all available locations that are provided by the cab service
     *
     * @return {@link Set<LocationDto>}all available locations
     */
    @GetMapping("location")
    public Set<LocationDto> displayAllLocations() {
        return rideService.displayAllLocations();
    }

    /**
     *
     * Shows all cab categories that are provided by the cab service
     *
     * @return {@link Set<CabCategoryDto>}all available categories
     */
    @GetMapping("categories")
    public Set<CabCategoryDto> displayAllCabCategories() {
        return rideService.displayAllCabCategories();
    }

    /**
     *
     * Book ride for a customer
     *
     * @param rideDto {@link RideDto} ride details of a customer
     * @return cabDtos {@link Set<CabDto>} list of cab that are
     * available on particular location
     *
     */
    @PostMapping("book/{categoryId}")
    public String bookRide(@RequestBody @Valid RideDto rideDto,
                           @PathVariable int categoryId) {
        return rideService.bookRide(rideDto, categoryId);
    }

    /**
     *
     * Often change the status of the ride and cab
     *
     * @param rideId {@link int} ride id
     * @param cabId {@link int} cab id to be assigned
     * @param rideStatus {@link String} ride status
     * @param cabStatus {@link String} cab status
     * @return {@link CabDto}assigned cab details
     */
    @PutMapping("status/{rideId}/{rideStatus}/{cabId}/{cabStatus}")
    public CabDto updateStatus(@PathVariable int rideId, @PathVariable int cabId,
                             @PathVariable String rideStatus,
                             @PathVariable String cabStatus) {
        return rideService.updateStatus(rideId, cabId, rideStatus, cabStatus);
    }

    /**
     *
     * After display the ride fare and if ride is booked
     *
     * @param rideId {@link int}
     * @return rideDto {@link RideDto} updated rideDto
     */
    @PostMapping("waiting/{rideId}")
    public String confirmRide(@PathVariable int rideId) {
        return rideService.confirmRide(rideId);
    }

    /**
     *
     * delete existing ride
     *
     * @param id {@link int} ride details to be updated
     * @return {@link String} deleted ride's id
     */
    @DeleteMapping("cancel/{id}")
    public String deleteRideById(@PathVariable int id){
        boolean isDeleted = rideService.deleteRideById(id);
        return isDeleted
                ? "Ride " + id + " Canceled Successfully"
                : "Ride " + id + " couldn't cancel selected ride";
    }
}
