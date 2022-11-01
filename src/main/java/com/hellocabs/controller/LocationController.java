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
import com.hellocabs.service.LocationService;

import org.apache.log4j.Logger;
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
     * @return int
     *         inserted location id is returned
     */
    @PostMapping("/create")
    private int addLocation(@Valid @RequestBody LocationDto locationDto) {
        int id = locationService.createLocation(locationDto);
        logger.info(HelloCabsConstants.LOCATION_CREATED);
        return  id;
    }

    /**
     * <p>
     * This method is to search location Details.
     * </p>
     *
     * @param id
     *        for which the id of the location need to
     *        be searched is given
     * @return LocationDto
     *         searched location is returned
     */
    @GetMapping("/search/{id}")
    private LocationDto searchLocationById(@PathVariable int id) {
        LocationDto locationDto = locationService.getLocationById(id);

        if (null == locationDto) {
            logger.error(HelloCabsConstants.LOCATION_NOT_FOUND);
            throw new HelloCabsException(HelloCabsConstants.LOCATION_NOT_FOUND);
        }
        logger.info(HelloCabsConstants.LOCATION_FOUND);
        return locationDto;
    }

    /**
     * <p>
     * This method is to update location Details.
     * </p>
     *
     * @param locationDto
     *        for which the location to be updated is given
     * @return LocationDto
     *         updated location is returned
     */
    @PutMapping("/update")
    private LocationDto updateLocation(@Valid @RequestBody LocationDto locationDto) {
        LocationDto updatedLocationDto = locationService.updateLocation(locationDto);
        if (null == updatedLocationDto ) {
            logger.error(HelloCabsConstants.LOCATION_NOT_FOUND);
            throw new HelloCabsException(HelloCabsConstants.LOCATION_NOT_FOUND);
        }
        logger.info(HelloCabsConstants.LOCATION_UPDATED);
        return updatedLocationDto;
    }

    /**
     * <p>
     * This method is to delete location Details.
     * </p>
     *
     * @param id
     *        for which the id of the location need to
     *        be deleted is given
     * @return String
     *         gets the message whether the location is
     *         deleted or not
     */
    @DeleteMapping("/delete/{id}")
    private String deleteLocationById(@PathVariable int id) {
        if (locationService.deleteLocationById(id)) {
            logger.info(HelloCabsConstants.LOCATION_DELETED);
            return HelloCabsConstants.LOCATION_DELETED;
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
     * @return List<LocationDto>
     *         retrieves all the locations
     */
    @GetMapping("/locations")
    private List<LocationDto> getAllLocations() {
        return locationService.retrieveAllLocations();
    }
}
