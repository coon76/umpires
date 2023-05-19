package org.umpires.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.umpires.dto.UmpireResponseDto;
import org.umpires.service.UmpireService;

import java.util.List;

@RestController
@RequestMapping(path = "/umpires", produces = "application/json")
public class UmpireController {
    @Autowired
    UmpireService umpireService;

    @GetMapping(path = "/{season}")
    public ResponseEntity<List<UmpireResponseDto>> getUmpiresBySeason(@PathVariable String season) {
        return ResponseEntity.ok(umpireService.getActiveUmpires(Integer.parseInt(season)));
    }

    @PostMapping(path = "/{season}/import")
    public ResponseEntity<Boolean> importUmpiresFromTeamSnap(@PathVariable String season) {
        umpireService.importUmpiresForSeason(Integer.parseInt(season));
        return ResponseEntity.ok(true);
    }

    @PutMapping
    public ResponseEntity<Boolean> saveUmpire(@RequestBody UmpireResponseDto umpire) {
        return ResponseEntity.ok(umpireService.saveUmpire(umpire));
    }
}
