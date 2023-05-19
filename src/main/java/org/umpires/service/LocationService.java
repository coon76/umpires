package org.umpires.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.umpires.dto.LocationDto;
import org.umpires.entity.Location;
import org.umpires.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<LocationDto> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        List<LocationDto> locationDtos = modelMapper.map(locations, new TypeToken<List<LocationDto>>() {}.getType());
        return locationDtos;
    }
}
