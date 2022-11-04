/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.service.impl;

import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.mapper.LocationMapper;
import com.hellocabs.model.Location;
import com.hellocabs.repository.LocationRepository;
import com.hellocabs.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final Logger logger = LoggerConfiguration.getInstance(HelloCabsConstants.LOCATION_SERVICE);
    private final LocationRepository locationRepository;

    /**
     * <p>
     *   This method is to add location Details.
     * </p>
     *
     * @param locationDto {@link LocationDto}
     *        for which the location to be added is given
     * @return {@link String} created location id
     *
     */
    public String createLocation(LocationDto locationDto) {
        Location location = LocationMapper.locationDtoToLocation(locationDto);

        if (!locationRepository.existsByLocationName(location.getLocationName())) {
            Integer id = LocationMapper.locationToLocationDto(locationRepository.save(location)).getId();
            logger.info(HelloCabsConstants.LOCATION_CREATED + id);
            return HelloCabsConstants.LOCATION_CREATED + id;
        }
        logger.error(HelloCabsConstants.LOCATION_ALREADY_EXISTS);
        throw new HelloCabsException(HelloCabsConstants.LOCATION_ALREADY_EXISTS);
    }

    /**
     * <p>
     *   This method is to search location Details.
     * </p>
     *
     * @param id {@link Integer}
     *        for which the id of the location need to
     *        be searched is given
     * @return {@link LocationDto} searched location
     *
     */
    public LocationDto getLocationById(Integer id) {
        Location location = locationRepository.findByIdAndIsDeleted(id, false);

        if (null != location) {
            logger.info(HelloCabsConstants.LOCATION_FOUND + id);
            return LocationMapper.locationToLocationDto(location);
        }
        logger.error(HelloCabsConstants.LOCATION_NOT_FOUND);
        throw new HelloCabsException(HelloCabsConstants.LOCATION_NOT_FOUND);
    }

    /**
     * <p>
     *   This method is to update location Details.
     * </p>
     *
     * @param locationDto {@link LocationDto}
     *        for which the location to be updated is given
     * @return {@link LocationDto}
     *         updated location is returned
     *
     */
    public LocationDto updateLocation(LocationDto locationDto) {

        if (locationRepository.existsByIdAndIsDeleted(locationDto.getId(), false)) {
            logger.info(HelloCabsConstants.LOCATION_UPDATED + locationDto.getId());
            return LocationMapper.locationToLocationDto(locationRepository.
                    save(LocationMapper.locationDtoToLocation(locationDto)));
        }
        logger.error(HelloCabsConstants.LOCATION_NOT_UPDATED);
        throw new HelloCabsException(HelloCabsConstants.LOCATION_NOT_UPDATED);
    }

    /**
     * <p>
     *   This method is to delete location Details.
     * </p>
     *
     * @param id {@link Integer}
     *        for which the id of the location need to
     *        be deleted is given
     * @return {@link String}
     *         gets the message whether the location is
     *         deleted or not
     *
     */
    public String deleteLocationById(Integer id) {
        Location location = locationRepository.findByIdAndIsDeleted(id, false);

        if (null != location) {
            location.setDeleted(true);
            locationRepository.save(location);
            logger.info(HelloCabsConstants.LOCATION_DELETED + id);
            return HelloCabsConstants.LOCATION_DELETED;
        }
        logger.error(HelloCabsConstants.LOCATION_NOT_FOUND);
        throw new HelloCabsException(HelloCabsConstants.LOCATION_NOT_FOUND);
    }

    /**
     * <p>
     *   This method is to display all location Details.
     * </p>
     *
     * @return {@link List<LocationDto>}
     *         retrieves all the locations
     */
    public List<LocationDto> retrieveAllLocations() {
        logger.info(HelloCabsConstants.ALL_LOCATIONS_FOUND);
        return LocationMapper.locationsToLocationDtos(locationRepository.findAllByIsDeleted(false));
    }
}

