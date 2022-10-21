package com.hellocabs.service;

import com.hellocabs.dto.LocationDto;

import java.util.List;

public interface LocationService {
    int createLocation(LocationDto locationDto);

    LocationDto updateLocation(LocationDto locationDto);

    LocationDto getLocationById(int id);

    boolean deleteLocationById(int id);

    List<LocationDto> retrieveAllLocations();
}
