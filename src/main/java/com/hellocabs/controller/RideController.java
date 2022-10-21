/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.controller;

import com.hellocabs.dto.RideDto;
import com.hellocabs.logger.LoggerConfiguration;
import com.hellocabs.service.RideService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 *
 * Implemented to create a new ride or search, update, and delete
 * existing ride
 *
 * This file is created on 20/10/2022
 * @author : Pradeep
 *
 */
@RestController
@RequestMapping("ride")
public class RideController {

    private RideService rideService;
    private Logger logger = LoggerConfiguration.getInstance("RideController.class");

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    /**
     *
     * Create ride whenever a customer books a cab
     *
     * @param rideDto {@link RideDto} ride details of a customer
     * @return {@link ResponseEntity<String>} created ride id
     *
     */
    @PostMapping("create")
    public ResponseEntity<String> createRide(@RequestBody RideDto rideDto) {
        int id = rideService.createRide(rideDto);
        return new ResponseEntity<>("Ride " + id
                + " Created Successfully", HttpStatus.CREATED);
    }

    /**
     *
     * Search particular ride by using id
     *
     * @param id {@link int} ride details to be searched
     * @return {@link ResponseEntity<RideDto>} ride details to be displayed
     *
     */
    @GetMapping("search/{id}")
    public ResponseEntity<RideDto> searchRideById(@PathVariable int id) {
        RideDto rideDto = rideService.searchRideById(id);
        return new ResponseEntity<>(rideDto, HttpStatus.FOUND);
    }

    /**
     *
     * retrieve all rides
     *
     * @return {@link Set<RideDto>} set of all rides
     *
     */
    @GetMapping("rides")
    public ResponseEntity<Set<RideDto>> retrieveRides() {
        Set<RideDto> rideDtos = rideService.retrieveRides();
        return new ResponseEntity<>(rideDtos, HttpStatus.FOUND);
    }



    /**
     *
     * Update particular ride by existing ride
     *
     * @param rideDto {@link RideDto} ride details to be updated
     * @return {@link ResponseEntity<String>} updated ride
     *
     */
    @PutMapping("update")
    public ResponseEntity<String> updateRide(@RequestBody RideDto rideDto){
        RideDto rideDto1 = rideService.updateRide(rideDto);
        return new ResponseEntity<>("Ride " + rideDto1.getId()
                + " Updated Successfully", HttpStatus.OK);
    }

    /**
     *
     * Update particular ride by existing ride
     *
     * @param id {@link int} ride details to be updated
     * @return {@link ResponseEntity<String>} deleted ride's id
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteRideById(@PathVariable int id){
        boolean isDeleted = rideService.deleteRideById(id);

        return isDeleted
                ? new ResponseEntity<>("Ride " + id
                        + " Deleted Successfully", HttpStatus.OK)
                : new ResponseEntity<>("Ride " + id
                        + " couldn't delete selected ride", HttpStatus.NOT_FOUND);
    }
}
