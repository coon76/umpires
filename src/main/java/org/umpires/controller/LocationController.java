package org.umpires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.umpires.dto.LocationDto;
import org.umpires.service.LocationService;

import java.util.List;

@RestController
@RequestMapping(path = "/locations", produces = "application/json")
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    //Add Locations
    //Load Locations
}
