package com.hellocabs.controller;

import com.hellocabs.dto.LocationDto;
import com.hellocabs.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    LocationService locationService;

    LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> addLocation(@RequestBody LocationDto locationDto) {
        int id = locationService.createLocation(locationDto);
        return  new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public LocationDto searchLocationById(@PathVariable int id) {
        LocationDto locationDto = locationService.getLocationById(id);

        if (locationDto == null) {
            return null;
        } else {
            return locationDto;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<LocationDto> updateLocation(@RequestBody LocationDto locationDto) {
        locationService.updateLocation(locationDto);
        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLocationById(@PathVariable int id) {
        if (locationService.deleteLocationById(id)) {
            return new ResponseEntity<>("Cab category is deleted for given id", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cab category is not found for given id", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/locations")
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        List<LocationDto> locationDtos = locationService.retrieveAllLocations();
        return new ResponseEntity<>(locationDtos, HttpStatus.OK);
    }
}
