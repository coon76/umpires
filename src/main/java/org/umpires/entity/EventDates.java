package org.umpires.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@Table(name = "event_dates")
public class EventDates {
    @Id
    private long id;
    private LocalDateTime gameTime;
    private EventType event;
    private boolean rainOut;

}
