package com.hellocabs.service;

import com.hellocabs.dto.LocationDto;
import com.hellocabs.model.Location;

public interface LocationService {
    int createLocation(LocationDto locationDto);

    // LocationDto updateLocationById();

    LocationDto getLocationById(int id);

    boolean deleteLocationById(int id);
}
