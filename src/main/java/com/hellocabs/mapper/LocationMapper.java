package com.hellocabs.mapper;

import com.hellocabs.configuration.MapperConfig;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.model.Location;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class LocationMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static LocationDto locationToLocationDto(Location location) {
        return modelMapper.map(location, LocationDto.class);
    }

    public static Location locationDtoToLocation(LocationDto locationDto) {
        return MapperConfig.getConfigure().map(locationDto, Location.class);
    }

    public static List<LocationDto> locationsToLocationDtos(List<Location> locations) {
        List<LocationDto> locationDtos = new ArrayList<LocationDto>();

        for (Location location: locations) {
            locationDtos.add(locationToLocationDto(location));
        }
        return locationDtos;
    }
}
