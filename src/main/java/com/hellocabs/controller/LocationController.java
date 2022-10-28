package com.hellocabs.controller;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.logger.LoggerConfiguration;
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
    public int addLocation(@Valid @RequestBody LocationDto locationDto) {
        int id = locationService.createLocation(locationDto);
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
    public LocationDto searchLocationById(@PathVariable int id) throws RuntimeException {
        LocationDto locationDto = locationService.getLocationById(id);

        if (locationDto == null) {
            throw new RuntimeException("Id not found");
        } else {
            return locationDto;
        }
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
    public LocationDto updateLocation(@Valid @RequestBody LocationDto locationDto) {
        LocationDto updatedLocationDto = locationService.updateLocation(locationDto);

        if (null == updatedLocationDto ) {
            throw new RuntimeException(HelloCabsConstants.LOCATION_NOT_FOUND);
        }
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
    public String deleteLocationById(@PathVariable int id) {
        if (locationService.deleteLocationById(id)) {
            return "Location is deleted for given id";
        } else {
            return "Location is not found for given id";
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
    public List<LocationDto> getAllLocations() {
        return locationService.retrieveAllLocations();
    }
}
