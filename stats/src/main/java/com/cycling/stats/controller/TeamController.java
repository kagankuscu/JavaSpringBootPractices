package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.TeamDto;
import com.cycling.stats.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class TeamController {
    private final TeamService teamService;

    @GetMapping(path = "/teams")
    public List<TeamDto> findAll() {
        return teamService.findAll();
    }

    @GetMapping(path = "/teams/{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable("id") Long id) {
        return teamService
                .findById(id)
                .map(team -> new ResponseEntity<>(team, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/teams")
    public ResponseEntity<TeamDto> create(@RequestBody TeamDto teamDto) {
        return new ResponseEntity<>(teamService.create(teamDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/teams/list")
    public ResponseEntity<List<TeamDto>> create(@RequestBody List<TeamDto> teamsDto) {
        return new ResponseEntity<>(teamService.createList(teamsDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/teams/{id}")
    public ResponseEntity<TeamDto> update(@PathVariable("id") Long id,
                                              @RequestBody TeamDto teamDto) {
        return teamService
                .update(id, teamDto)
                .map(team -> new ResponseEntity<>(team, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/teams/{id}")
    public ResponseEntity<TeamDto> partialUpdate(@PathVariable("id") Long id,
                                                     @RequestBody TeamDto teamDto) {
        return teamService
                .partialUpdate(id, teamDto)
                .map(team -> new ResponseEntity<>(team, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping(path = "/teams/{id}")
    public ResponseEntity<TeamDto> delete(@PathVariable("id") Long id) {
        if (!teamService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/teams/{id}/delete")
    public ResponseEntity<TeamDto> softDelete(@PathVariable("id") Long id) {
        return teamService
                .softDelete(id)
                .map(teamDto -> new ResponseEntity<>(teamDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
