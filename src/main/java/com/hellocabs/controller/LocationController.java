/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.controller;

import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.response.HelloCabsResponseHandler;
import com.hellocabs.service.LocationService;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
/**
 * <p>
 * Location controller class has the methods that handles
 * CRUD operations of location.
 * </p>
 *
 * @author  Divya
 *
 * @version 1.0 Oct-26-2022
 *
 */
@RestController
@RequestMapping("/location")
public class LocationController {
    private Logger logger = LoggerConfiguration.getInstance("LocationController.class");
    LocationService locationService;

    LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * <p>
     * This method is to add location Details.
     * </p>
     *
     * @param locationDto
     *        for which the location to be added is given
     * @return ResponseEntity<Object>
     *         inserted location id is returned
     */
    @PostMapping("/create")
    private ResponseEntity<Object> addLocation(@Valid @RequestBody LocationDto locationDto) {
        return HelloCabsResponseHandler.generateResponse(locationService.createLocation(locationDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is to search location Details.
     * </p>
     *
     * @param id
     *        for which the id of the location need to
     *        be searched is given
     * @return ResponseEntity<Object>
     *         searched location is returned
     */
    @GetMapping("/search/{id}")
    private ResponseEntity<Object> searchLocationById(@PathVariable Integer id) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                .LOCATION_FOUND, HttpStatus.FOUND, locationService.getLocationById(id));
    }

    /**
     * <p>
     * This method is to update location Details.
     * </p>
     *
     * @param locationDto
     *        for which the location to be updated is given
     * @return ResponseEntity<Object>
     *         updated location is returned
     */
    @PutMapping("/update")
    private ResponseEntity<Object> updateLocation( @Valid @RequestBody LocationDto locationDto) {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                    .LOCATION_UPDATED, HttpStatus.OK, locationService.updateLocation(locationDto));
    }

    /**
     * <p>
     * This method is to delete location Details.
     * </p>
     *
     * @param id {@link Integer}
     *        for which the id of the location need to
     *        be deleted is given
     * @return ResponseEntity<Object>
     *         gets the message whether the location is
     *         deleted or not
     */
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Object> deleteLocationById(@PathVariable Integer id) {
        return HelloCabsResponseHandler.generateResponse(locationService.deleteLocationById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is to display all location Details.
     * </p>
     *
     * @return ResponseEntity<Object>
     *         retrieves all the locations
     */
    @GetMapping("/locations")
    private ResponseEntity<Object> getAllLocations() {
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants
                .LOCATION_FOUND, HttpStatus.OK, locationService
                .retrieveAllLocations());
    }
}
