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
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", teamService.findById(id)),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(
                    new ApiResponse(e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse> create(@RequestBody AddTeamDto teamDto) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", teamService.create(teamDto)),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping(path = "/list")
    public ResponseEntity<ApiResponse> create(@RequestBody List<AddTeamDto> teamDtos) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", teamService.createList(teamDtos)),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                             @RequestBody UpdateTeamDto updateTeamDto) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", teamService.update(id, updateTeamDto)),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException | UpdateIdNotEqualGivenException e) {
            return new ResponseEntity<>(
                    new ApiResponse(e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> partialUpdate(@PathVariable("id") Long id,
                                                    @RequestBody UpdateTeamDto updateTeamDto) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", teamService.partialUpdate(id, updateTeamDto)),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException | UpdateIdNotEqualGivenException e) {
            return new ResponseEntity<>(
                    new ApiResponse(e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        try {
            teamService.delete(id);
            return new ResponseEntity<>(
                    new ApiResponse("success", null),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(
                    new ApiResponse(
                            e.getMessage(), null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping(path = "/{id}/delete")
    public ResponseEntity<ApiResponse> softDelete(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", teamService.softDelete(id)),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(
                    new ApiResponse(
                            e.getMessage(), null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
