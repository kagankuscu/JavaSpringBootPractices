package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.teamContractDtos.AddTeamContractDto;
import com.cycling.stats.domain.dtos.teamContractDtos.GetTeamContractDto;
import com.cycling.stats.domain.dtos.teamContractDtos.UpdateTeamContractDto;
import com.cycling.stats.exceptions.ResourceNotFoundException;
import com.cycling.stats.exceptions.UpdateIdNotEqualGivenException;
import com.cycling.stats.response.ApiResponse;
import com.cycling.stats.services.TeamContractService;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "${api.prefix}/team-contract")
public class TeamContractController {
    private final TeamContractService teamContractService;

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse> findAll() {
        return new ResponseEntity<>(
                new ApiResponse("success", teamContractService.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                new ApiResponse(
            "success",
                teamContractService
                            .findById(id)
            ), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse> create(@RequestBody AddTeamContractDto addTeamContractDto) {
        return new ResponseEntity<>(
                new ApiResponse("success", teamContractService.create(addTeamContractDto)),
                HttpStatus.CREATED
        );
    }

    @PostMapping(path = "/list")
    public ResponseEntity<ApiResponse> create(@RequestBody List<AddTeamContractDto> addTeamContractDtos) {
        return new ResponseEntity<>(
                new ApiResponse("success", teamContractService.createList(addTeamContractDtos)),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                                     @RequestBody UpdateTeamContractDto updateTeamContractDto) {
        return new ResponseEntity<>(
                new ApiResponse("success", teamContractService.update(id, updateTeamContractDto)),
                HttpStatus.OK
        );
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> partialUpdate(@PathVariable("id") Long id,
                                                            @RequestBody UpdateTeamContractDto updateTeamContractDto) {
        return new ResponseEntity<>(
                new ApiResponse("success", teamContractService.partialUpdate(id, updateTeamContractDto)),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        teamContractService.delete(id);
        return new ResponseEntity<>(
                new ApiResponse("success", null),
                HttpStatus.OK
        );
    }

    @PutMapping(path = "/{id}/delete")
    public ResponseEntity<ApiResponse> softDelete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                new ApiResponse("success", teamContractService.softDelete(id)),
                HttpStatus.OK
        );
    }
}
