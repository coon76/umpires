package org.umpires.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@Table(name = "umpire_season")
public class UmpireSeason {
    @Id
    private long id;
    @Column(name = "umpire_id")
    private long umpireId;
    @Column
    private int season;
}
