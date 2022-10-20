package com.hellocabs.mapper;

import com.hellocabs.dto.LocationDto;
import com.hellocabs.model.Location;

public class LocationMapper {
    public static LocationDto LocationToLocationDto(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setLocationName(location.getLocationName());
        return locationDto;
    }

    public static Location LocationDtoToLocation(LocationDto locationDto) {
        Location location = new Location();
        location.setLocationName(locationDto.getLocationName());
        return location;
    }
}
