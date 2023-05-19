package org.umpires.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.umpires.client.TeamSnapClient;
import org.umpires.client.model.dto.TeamSnapUmpire;
import org.umpires.dto.UmpireResponseDto;
import org.umpires.entity.Umpire;
import org.umpires.entity.UmpireSeason;
import org.umpires.repository.UmpireRepository;
import org.umpires.repository.UmpireSeasonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UmpireService {
    @Autowired
    private UmpireRepository umpireRepository;

    @Autowired
    private UmpireSeasonRepository umpireSeasonRepository;

    private TeamSnapClient teamSnapClient;

    private UmpireService() {
        teamSnapClient = new TeamSnapClient();
    }

    public List<UmpireResponseDto> getActiveUmpires(int season) {
        List<Umpire> umpires = umpireRepository.findBySeasonsSeason(season);
        System.out.println(umpireRepository.getMaxId());
        List<UmpireResponseDto> umpireResponses = new ArrayList<>();
        umpires.forEach(umpire -> {
            umpireResponses.add(new UmpireResponseDto(umpire, season));
        });
        return umpireResponses;
    }

    public boolean importUmpiresForSeason(int season) {
        //Call Team Snap - create team snap client to obscure team snap api.
        List<TeamSnapUmpire> tsUmpires = teamSnapClient.getUmpires();
        tsUmpires.forEach(u -> {
            Umpire umpire = umpireRepository.findByFirstNameAndLastName(u.getFirstName(), u.getLastName());
            UmpireSeason us = new UmpireSeason();
            if (umpire == null) {
                long umpId = getNextUmpireId();
                umpire = new Umpire();
                umpire.setId(umpId);
                umpire.setFirstName(u.getFirstName());
                umpire.setLastName(u.getLastName());
                List<UmpireSeason> seasons = new ArrayList<>();
                us.setId(getNextUmpireSeasonId());
                us.setSeason(season);
                us.setUmpireId(umpId);
                seasons.add(us);
            } else {
                if (umpire.getSeasons().stream().filter(umpireSeason -> umpireSeason.getSeason() == season).collect(Collectors.toList()).size() == 0) {
                    us.setId(getNextUmpireSeasonId());
                    us.setUmpireId(umpire.getId());
                    us.setSeason(season);
                } else {
                    us = null;
                }
            }

            umpireRepository.saveAndFlush(umpire);
            if (us != null) {
                umpireSeasonRepository.saveAndFlush(us);
            }
        });
        return true;
    }

    public boolean saveUmpire(UmpireResponseDto newUmpire) {
        Optional<Umpire> optionalUmpire = umpireRepository.findById(newUmpire.getId());
        Umpire umpire = newUmpire.convertToUmpire();
        if (!optionalUmpire.isPresent()) {
            umpire.setId(getNextUmpireId());
        } else {
            umpire.setSeasons(optionalUmpire.get().getSeasons());
        }
        umpireRepository.saveAndFlush(umpire);

        return true;
    }

    private long getNextUmpireId() {
        return umpireRepository.getMaxId() + 1;
    }

    private long getNextUmpireSeasonId() {
        return umpireSeasonRepository.getMaxId() + 1;
    }
}
