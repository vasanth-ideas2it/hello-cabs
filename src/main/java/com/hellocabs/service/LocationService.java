/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.service;

import com.hellocabs.dto.LocationDto;

import java.util.List;

public interface LocationService {
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
    int createLocation(LocationDto locationDto);

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
    LocationDto updateLocation(LocationDto locationDto);

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
    LocationDto getLocationById(int id);

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
    boolean deleteLocationById(int id);

    /**
     * <p>
     * This method is to display all location Details.
     * </p>
     *
     * @return List<LocationDto>
     *         retrieves all the locations
     */
    List<LocationDto> retrieveAllLocations();
}
