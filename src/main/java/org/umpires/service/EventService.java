package org.umpires.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.umpires.dto.EventDto;
import org.umpires.entity.EventDates;
import org.umpires.entity.EventType;
import org.umpires.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ModelMapper modelMapper;

    public boolean loadGamesFromCSV(String csvFile, boolean skipFirstLine) {
        List<String> lines = Arrays.asList(csvFile.split("\\R"));
        final boolean[] skipped = {false};
        final LocalDateTime[] lastEventDate = {null};
        lines.forEach(l -> {

            if (skipFirstLine  && skipped[0]) {
                String[] eventFields = l.split(",");
                LocalDateTime currentEventDate = LocalDateTime.parse(removeQuotes(eventFields[0]) + "T" + removeQuotes(eventFields[1]));
                if (lastEventDate[0] == null || !lastEventDate[0].equals(currentEventDate)) {
                    EventDates eventDate = new EventDates();
                    eventDate.setGameTime(currentEventDate);
                    eventDate.setEvent(EventType.GAME);
                    eventDate.setRainOut(false);

                    lastEventDate[0] = currentEventDate;
                }
            } else {
                skipped[0] = true;
            }

        });
        return true;
    }

    private String removeQuotes(String value) {
        return value.replaceAll("\"", "");
    }

    public List<EventDto> getAllEventDates() {
        List<EventDates> eventDates = eventRepository.findAll();
        return eventDates.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    private EventDto convertToDto(EventDates eventDates) {
        EventDto eventDto = modelMapper.map(eventDates, EventDto.class);
        return eventDto;
    }
}
