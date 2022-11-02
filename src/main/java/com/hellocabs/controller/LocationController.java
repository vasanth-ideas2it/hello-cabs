/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.controller;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.configuration.LoggerConfiguration;
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

import java.util.List;

import javax.validation.Valid;

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
        String locationCreated = locationService.createLocation(locationDto);
        logger.info(locationCreated);
        return  HelloCabsResponseHandler.generateResponse(locationCreated, HttpStatus.CREATED);
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
    private ResponseEntity<Object> searchLocationById(@PathVariable int id) {
        LocationDto locationDto = locationService.getLocationById(id);

        if (null == locationDto) {
            logger.error(HelloCabsConstants.LOCATION_NOT_FOUND);
            throw new HelloCabsException(HelloCabsConstants.LOCATION_NOT_FOUND);
        }
        logger.info(HelloCabsConstants.LOCATION_FOUND);
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.LOCATION_FOUND, HttpStatus.FOUND, locationDto);
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
    @PutMapping("/update/{id}")
    private ResponseEntity<Object> updateLocation(@PathVariable int id, @Valid @RequestBody LocationDto locationDto) {
        LocationDto updatedLocationDto = locationService.updateLocation(id, locationDto);
        if (null == updatedLocationDto ) {
            logger.error(HelloCabsConstants.LOCATION_NOT_FOUND);
            throw new HelloCabsException(HelloCabsConstants.LOCATION_NOT_FOUND);
        }
        logger.info(HelloCabsConstants.LOCATION_UPDATED);
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.LOCATION_UPDATED, HttpStatus.OK, updatedLocationDto);
    }

    /**
     * <p>
     * This method is to delete location Details.
     * </p>
     *
     * @param id
     *        for which the id of the location need to
     *        be deleted is given
     * @return ResponseEntity<Object>
     *         gets the message whether the location is
     *         deleted or not
     */
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Object> deleteLocationById(@PathVariable int id) {
        if (locationService.deleteLocationById(id)) {
            logger.info(HelloCabsConstants.LOCATION_DELETED);
            return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.LOCATION_DELETED, HttpStatus.OK);
        } else {
            logger.info(HelloCabsConstants.LOCATION_NOT_FOUND);
            throw new HelloCabsException(HelloCabsConstants.LOCATION_NOT_FOUND);
        }
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
        logger.info(HelloCabsConstants.LOCATION_FOUND);
        return HelloCabsResponseHandler.generateResponse(HelloCabsConstants.LOCATION_FOUND, HttpStatus.OK, locationService.retrieveAllLocations());
    }
}
