package com.hellocabs.service.impl;

import com.hellocabs.dto.LocationDto;
import com.hellocabs.mapper.LocationMapper;
import com.hellocabs.model.Location;
import com.hellocabs.repository.LocationRepository;
import com.hellocabs.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Location> location = locationRepository.findById(id);

        if (!location.isPresent()) {
            return null;
        } else {
            LocationDto locationDto = LocationMapper.locationToLocationDto(location.get());
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
    public LocationDto updateLocation(LocationDto locationDto) {
        Location location = LocationMapper.locationDtoToLocation(locationDto);

        return LocationMapper.locationToLocationDto(locationRepository.save(location));
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
        Optional<Location> location = locationRepository.findById(id);

        if (!location.isPresent()) {
            return false;
        } else {
            locationRepository.deleteById(id);
            return true;
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
        return LocationMapper.locationsToLocationDtos(locationRepository.findAll());
    }
}

