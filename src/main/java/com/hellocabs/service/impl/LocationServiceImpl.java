/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service.impl;

import com.hellocabs.dto.LocationDto;
import com.hellocabs.mapper.LocationMapper;
import com.hellocabs.model.Location;
import com.hellocabs.repository.LocationRepository;
import com.hellocabs.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    LocationRepository locationRepository;

    LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
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
    public int createLocation(LocationDto locationDto) {
        Location location = LocationMapper.locationDtoToLocation(locationDto);
        int id = LocationMapper.locationToLocationDto(locationRepository.save(location)).getId();
        return id;
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
    public LocationDto getLocationById(int id) {
        Location location = locationRepository.findByIdAndIsDeleted(id, false);

        if (null != location) {
            return LocationMapper.locationToLocationDto(location);
        } else {
            return null;
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
    public LocationDto updateLocation(LocationDto locationDto) {
        Location location = LocationMapper.locationDtoToLocation(locationDto);

        if (locationRepository.existsByIdAndIsDeleted(location.getId(), false)) {
            return LocationMapper.locationToLocationDto(locationRepository.save(location));
        } else {
            return null;
        }
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
    public boolean deleteLocationById(int id) {
        Location location = locationRepository.findByIdAndIsDeleted(id, false);

        if (null != location) {
            location.setDeleted(true);
            locationRepository.save(location);
            return true;
        } else {
            return false;
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
    public List<LocationDto> retrieveAllLocations() {
        return LocationMapper.locationsToLocationDtos(locationRepository.findAllByIsDeleted(false));
    }
}

