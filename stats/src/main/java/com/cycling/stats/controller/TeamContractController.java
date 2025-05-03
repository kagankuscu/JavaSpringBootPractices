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
@RequestMapping(path = "${api.prefix}")
public class TeamContractController {
    private final TeamContractService teamContractService;

    @GetMapping(path = "/teamcontract")
    public ResponseEntity<ApiResponse> findAll() {
        return new ResponseEntity<>(
                new ApiResponse("success", teamContractService.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/teamcontract/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse(
                "success",
                    teamContractService
                                .findById(id)
                ), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping(path = "/teamcontract")
    public ResponseEntity<ApiResponse> create(@RequestBody AddTeamContractDto addTeamContractDto) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", teamContractService.create(addTeamContractDto)),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping(path = "/teamcontract/list")
    public ResponseEntity<ApiResponse> create(@RequestBody List<AddTeamContractDto> addTeamContractDtos) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", teamContractService.createList(addTeamContractDtos)),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping(path = "/teamcontract/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                                     @RequestBody UpdateTeamContractDto updateTeamContractDto) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", teamContractService.update(id, updateTeamContractDto)),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException | UpdateIdNotEqualGivenException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "/teamcontract/{id}")
    public ResponseEntity<ApiResponse> partialUpdate(@PathVariable("id") Long id,
                                                            @RequestBody UpdateTeamContractDto updateTeamContractDto) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", teamContractService.partialUpdate(id, updateTeamContractDto)),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException | UpdateIdNotEqualGivenException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/teamcontract/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        try {
            teamContractService.delete(id);
            return new ResponseEntity<>(
                    new ApiResponse("success", null),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(
                    new ApiResponse(e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping(path = "/teamcontract/{id}/delete")
    public ResponseEntity<ApiResponse> softDelete(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", teamContractService.softDelete(id)),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(
                    new ApiResponse(e.getMessage(), null),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
