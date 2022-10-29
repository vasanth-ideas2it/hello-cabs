package com.hellocabs.mapper;

import com.hellocabs.dto.LocationDto;
import com.hellocabs.model.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationMapper {
    public static LocationDto locationToLocationDto(Location location) {
        return new LocationDto(location.getId(), location.getLocationName());
    }

    public static Location locationDtoToLocation(LocationDto locationDto) {
        return new Location(locationDto.getId(), locationDto.getLocationName());
    }

    public static List<LocationDto> locationsToLocationDtos(List<Location> locations) {
        List<LocationDto> locationDtos = new ArrayList<LocationDto>();

        for (Location location: locations) {
            locationDtos.add(locationToLocationDto(location));
        }
        return locationDtos;
    }
}
