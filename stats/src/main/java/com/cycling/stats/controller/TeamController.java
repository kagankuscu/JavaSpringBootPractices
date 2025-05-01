package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.teamDtos.AddTeamDto;
import com.cycling.stats.domain.dtos.teamDtos.GetTeamDto;
import com.cycling.stats.domain.dtos.teamDtos.UpdateTeamDto;
import com.cycling.stats.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(path = "${api.prefix}/teams")
public class TeamController {
    private final TeamService teamService;

    @GetMapping(path = "")
    public List<GetTeamDto> findAll() {
        return teamService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GetTeamDto> getTeam(@PathVariable("id") Long id) {
        return teamService
                .findById(id)
                .map(team -> new ResponseEntity<>(team, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "")
    public ResponseEntity<GetTeamDto> create(@RequestBody AddTeamDto teamDto) {
        return new ResponseEntity<>(teamService.create(teamDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/list")
    public ResponseEntity<List<GetTeamDto>> create(@RequestBody List<AddTeamDto> teamsDto) {
        return new ResponseEntity<>(teamService.createList(teamsDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<GetTeamDto> update(@PathVariable("id") Long id,
                                             @RequestBody UpdateTeamDto updateTeamDto) {
        return teamService
                .update(id, updateTeamDto)
                .map(team -> new ResponseEntity<>(team, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<GetTeamDto> partialUpdate(@PathVariable("id") Long id,
                                                    @RequestBody UpdateTeamDto updateTeamDto) {
        return teamService
                .partialUpdate(id, updateTeamDto)
                .map(team -> new ResponseEntity<>(team, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<GetTeamDto> delete(@PathVariable("id") Long id) {
        if (!teamService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/delete")
    public ResponseEntity<GetTeamDto> softDelete(@PathVariable("id") Long id) {
        return teamService
                .softDelete(id)
                .map(teamDto -> new ResponseEntity<>(teamDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
