package com.hellocabs.service.impl;

import com.hellocabs.dto.CabCategoryDto;
import com.hellocabs.dto.LocationDto;
import com.hellocabs.mapper.CabCategoryMapper;
import com.hellocabs.mapper.LocationMapper;
import com.hellocabs.model.CabCategory;
import com.hellocabs.model.Location;
import com.hellocabs.repository.LocationRepository;
import com.hellocabs.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    LocationRepository locationRepository;

    LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    public int createLocation(LocationDto locationDto) {
        Location location = LocationMapper.LocationDtoToLocation(locationDto);
        int id = LocationMapper.LocationToLocationDto(locationRepository.save(location)).getId();
        return id;
    }

    // public Location updateLocationById(LocationDto locationDto);

    public LocationDto getLocationById(int id) {
        Optional<Location> location = locationRepository.findById(id);

        if (!location.isPresent()) {
            return null;
        } else {
            LocationDto locationDto = LocationMapper.LocationToLocationDto(location.get());
            return locationDto;
        }
    }

    public boolean deleteLocationById(int id) {
        Optional<Location> location = locationRepository.findById(id);

        if (!location.isPresent()) {
            return false;
        } else {
            locationRepository.deleteById(id);
            return true;
        }
    }
}
