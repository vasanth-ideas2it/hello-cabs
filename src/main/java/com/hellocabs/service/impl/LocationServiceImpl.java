/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service.impl;

import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.mapper.LocationMapper;
import com.hellocabs.model.Location;
import com.hellocabs.repository.LocationRepository;
import com.hellocabs.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    /**
     * <p>
     * This method is to add location Details.
     * </p>
     *
     * @param locationDto
     *        for which the location to be added is given
     * @return Integer
     *         inserted location id is returned
     */
    public String createLocation(LocationDto locationDto) {
        Location location = LocationMapper.locationDtoToLocation(locationDto);

        if (!locationRepository.existsByLocationName(location.getLocationName())) {
            Integer id = LocationMapper.locationToLocationDto
                    (locationRepository.save(location)).getId();
            return HelloCabsConstants.LOCATION_CREATED + id;
        }
        return HelloCabsConstants.LOCATION_ALREADY_EXISTS;
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
    public LocationDto getLocationById(Integer id) {
        Location location = locationRepository.findByIdAndIsDeleted(id, false);

        if (null != location) {
            return LocationMapper.locationToLocationDto(location);
        }
        throw new HelloCabsException(HelloCabsConstants.LOCATION_NOT_FOUND);
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
    public LocationDto updateLocation( LocationDto locationDto) {

        if (locationRepository.existsByIdAndIsDeleted(locationDto.getId(), false)) {
            return LocationMapper.locationToLocationDto(locationRepository.
                    save(LocationMapper.locationDtoToLocation(locationDto)));
        }
        throw new HelloCabsException(HelloCabsConstants.LOCATION_NOT_UPDATED);
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
    public boolean deleteLocationById(Integer id) {
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

