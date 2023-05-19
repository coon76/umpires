package org.umpires.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Data
@Table(name = "game_umpire")
public class GameUmpire {
    @Id
    private long id;
    private long gameDateId;
    private long umpireId;
    private UmpireType umpireType;
}
