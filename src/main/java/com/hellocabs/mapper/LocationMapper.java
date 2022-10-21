package com.hellocabs.mapper;

import com.hellocabs.dto.LocationDto;
import com.hellocabs.model.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationMapper {
    public static LocationDto LocationToLocationDto(Location location) {
        LocationDto locationDto = new LocationDto();

        locationDto.setId(location.getId());
        locationDto.setLocationName(location.getLocationName());
        return locationDto;
    }

    public static Location LocationDtoToLocation(LocationDto locationDto) {
        Location location = new Location();

        location.setId(locationDto.getId());
        location.setLocationName(locationDto.getLocationName());
        return location;
    }

    public static List<LocationDto> locationsToLocationDtos(List<Location> locations) {
        List<LocationDto> locationDtos = new ArrayList<LocationDto>();

        for (Location location: locations) {
            locationDtos.add(LocationToLocationDto(location));
        }
        return locationDtos;
    }
}
