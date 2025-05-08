package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.teamDtos.AddTeamDto;
import com.cycling.stats.domain.dtos.teamDtos.GetTeamDto;
import com.cycling.stats.domain.dtos.teamDtos.UpdateTeamDto;
import com.cycling.stats.exceptions.ResourceNotFoundException;
import com.cycling.stats.exceptions.UpdateIdNotEqualGivenException;
import com.cycling.stats.response.ApiResponse;
import com.cycling.stats.services.TeamService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
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
    public ResponseEntity<ApiResponse> findAll() {
        return new ResponseEntity<>(
                new ApiResponse("success", teamService.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> getTeam(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                new ApiResponse("success", teamService.findById(id)),
                HttpStatus.OK
        );
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse> create(@RequestBody AddTeamDto teamDto) {
        return new ResponseEntity<>(
                new ApiResponse("success", teamService.create(teamDto)),
                HttpStatus.OK
        );
    }

    @PostMapping(path = "/list")
    public ResponseEntity<ApiResponse> create(@RequestBody List<AddTeamDto> teamDtos) {
        return new ResponseEntity<>(
                new ApiResponse("success", teamService.createList(teamDtos)),
                HttpStatus.OK
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                             @RequestBody UpdateTeamDto updateTeamDto) {
        return new ResponseEntity<>(
                new ApiResponse("success", teamService.update(id, updateTeamDto)),
                HttpStatus.OK
        );
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> partialUpdate(@PathVariable("id") Long id,
                                                    @RequestBody UpdateTeamDto updateTeamDto) {
        return new ResponseEntity<>(
                new ApiResponse("success", teamService.partialUpdate(id, updateTeamDto)),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        teamService.delete(id);
        return new ResponseEntity<>(
                new ApiResponse("success", null),
                HttpStatus.OK
        );
    }

    @PutMapping(path = "/{id}/delete")
    public ResponseEntity<ApiResponse> softDelete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                new ApiResponse("success", teamService.softDelete(id)),
                HttpStatus.OK
        );
    }
}
