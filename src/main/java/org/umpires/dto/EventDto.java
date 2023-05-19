package org.umpires.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDto {
    private long eventId;
    private LocalDateTime gameTime;
    private String eventType;
    private boolean rainOut;
}
