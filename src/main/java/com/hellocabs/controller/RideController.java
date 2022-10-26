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
import com.hellocabs.service.CabCategoryService;
import com.hellocabs.service.CabService;
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

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private CabService cabService;
    private CabCategoryService cabCategoryService;
    private Logger logger = LoggerConfiguration.getInstance("RideController.class");

    public RideController(RideService rideService, CabService cabService,
                          CabCategoryService cabCategoryService) {
        this.rideService = rideService;
        this.cabService = cabService;
        this.cabCategoryService = cabCategoryService;
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
    public String createRide(@RequestBody RideDto rideDto) {
        int id = rideService.createRide(rideDto);
        return "Ride " + id + " Created Successfully";
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
    @GetMapping("rides")
    public Set<RideDto> retrieveRides() {
        return rideService.retrieveRides();
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
    public String updateRide(@RequestBody RideDto rideDto){

        if (null != rideService.updateRide(rideDto)) {
            return "Ride " + rideDto.getId() + " Updated Successfully";
        }
        return "Couldn't update ride";
    }

    /**
     *
     * Update particular ride by existing ride
     *
     * @param id {@link int} ride details to be updated
     * @return {@link ResponseEntity<String>} deleted ride's id
     */
    @DeleteMapping("delete/{id}")
    public String deleteRideById(@PathVariable int id){
        boolean isDeleted = rideService.deleteRideById(id);
        return isDeleted
                ? "Ride " + id + " Deleted Successfully"
                : "Ride " + id + " couldn't delete selected ride";
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
    @PostMapping("book")
    public Set<CabDto> bookRide(@RequestBody RideDto rideDto) {
        LocationDto pickupPoint = rideDto.getPickupLocation();
        LocationDto dropPoint = rideDto.getDropLocation();
        logger.info(rideDto);
        logger.info(pickupPoint);
        logger.info(pickupPoint.getLocationName());
        int id = rideService.createRide(rideDto);
        Set<CabDto> cabDtos = new HashSet<>();
        cabCategoryService.retrieveAllCabCategories()
                .forEach(cabCategoryDto -> cabCategoryDto.getCabDtos()
                .stream().filter(cabDto -> pickupPoint.getLocationName()
                .equals(cabDto.getCurrentLocation()) && (("Available")
                .equalsIgnoreCase(cabDto.getCabStatus())))
                .forEach(cabDtos :: add));
        return cabDtos;

        /*Set<CabCategoryDto> cabCategoryDtos = new HashSet<>();
        cabCategoryService.retrieveAllCabCategories()
                .forEach(cabCategoryDto -> cabCategoryDto.getCabDtos()
                        .stream().filter(cabDto -> pickupPoint.getLocationName()
                                .equals(cabDto.getCurrentLocation()) && (("Available")
                                .equalsIgnoreCase(cabDto.getCabStatus()))).collect(Collectors.toSet()));
        return cabCategoryDtos;*/
    }

    /**
     *
     * Choose suitable cab category for the ride
     *
     * @param id {@link int} category id
     * @return {@link Double} base fare based on the category that
     */
    @GetMapping("chooseCategory/{id}")
    public Double chooseCategory(@PathVariable int id) {
        CabCategoryDto cabCategoryDto = cabCategoryService.getCabCategoryById(id);
        return cabCategoryDto.getInitialFare();
    }

    /**
     * After display the ride fare and if ride is booked
     * @param rideDto {@link RideDto}
     * @return rideDto {@link RideDto} updated rideDto
     */
    @PostMapping("confirm")
    public RideDto confirmRide(@RequestBody RideDto rideDto) {
        rideDto.setRideStatus("Waiting for cab to accept");
        rideService.updateRide(rideDto);
        return rideDto;
    }




}
