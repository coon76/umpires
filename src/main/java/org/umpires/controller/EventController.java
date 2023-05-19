package org.umpires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.umpires.dto.EventDto;
import org.umpires.service.EventService;

import java.util.List;

@RestController
@RequestMapping(path = "/events", produces = "application/json")
public class EventController {
    @Autowired
    private EventService eventService;

    //load game dates from csv
    //get events by id
    //add event

    @GetMapping
    public ResponseEntity<List<EventDto>> getEvents() {
        return ResponseEntity.ok(eventService.getAllEventDates());
    }

    @PostMapping(path = "/load")
    public ResponseEntity<Boolean> loadEvents(@RequestBody String csvFile) {
        return ResponseEntity.ok(eventService.loadGamesFromCSV(csvFile, true));
    }
}
