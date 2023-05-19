package org.umpires.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.umpires.entity.Umpire;

import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UmpireResponseDto {
    private long id;
    private String firstName;
    private String lastName;
    private boolean senior;
    private int yearsOfExperience;

    public UmpireResponseDto(Umpire umpire, int season) {
        id = umpire.getId();
        firstName = umpire.getFirstName();
        lastName = umpire.getLastName();
        senior = umpire.isSenior();
        yearsOfExperience = umpire.getSeasons().stream().filter(umpireSeason -> umpireSeason.getSeason() <= season).collect(Collectors.toList()).size() - 1;  //There is a minus one to remove the current season as experience
    }

    public Umpire convertToUmpire() {
        Umpire umpire = new Umpire();
        umpire.setId(id);
        umpire.setFirstName(firstName);
        umpire.setLastName(lastName);
        umpire.setSenior(senior);
        return umpire;
    }
}
