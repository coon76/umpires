package org.umpires.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@Table(name = "umpire")
public class Umpire {
    @Id
    private long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private boolean senior;

    @OneToMany
    @JoinColumn(name = "umpire_id")
    private List<UmpireSeason> seasons;
}
