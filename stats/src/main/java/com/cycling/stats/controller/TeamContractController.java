package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.TeamContractDto;
import com.cycling.stats.services.TeamContractService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class TeamContractController {
    private final TeamContractService teamContractService;

    @GetMapping(path = "/teamcontract")
    public List<TeamContractDto> findAll() {
        return teamContractService.findAll();
    }

    @GetMapping(path = "/teamcontract/{id}")
    public ResponseEntity<TeamContractDto> findById(@PathVariable("id") Long id) {
        return teamContractService
                .findById(id)
                .map(teamContract -> { return new ResponseEntity<>(teamContract, HttpStatus.OK); })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/teamcontract")
    public ResponseEntity<TeamContractDto> create(@RequestBody TeamContractDto teamContractDto) {
        return new ResponseEntity<>(teamContractService.create(teamContractDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/teamcontract/list")
    public ResponseEntity<List<TeamContractDto>> create(@RequestBody List<TeamContractDto> teamContractDtos) {
        return new ResponseEntity<>(teamContractService.createList(teamContractDtos), HttpStatus.CREATED);
    }

    @PutMapping(path = "/teamcontract/{id}")
    public ResponseEntity<TeamContractDto> update(@PathVariable("id") Long id,
                                           @RequestBody TeamContractDto teamContractDto) {
        return teamContractService
                .update(id, teamContractDto)
                .map(teamContract -> new ResponseEntity<>(teamContract, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/teamcontract/{id}")
    public ResponseEntity<TeamContractDto> partialUpdate(@PathVariable("id") Long id,
                                                  @RequestBody TeamContractDto teamContractDto) {
        return teamContractService
                .partialUpdate(id, teamContractDto)
                .map(teamContract -> new ResponseEntity<>(teamContract, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/teamcontract/{id}")
    public ResponseEntity<TeamContractDto> delete(@PathVariable("id") Long id) {
        if (!teamContractService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/teamcontract/{id}/delete")
    public ResponseEntity<TeamContractDto> softDelete(@PathVariable("id") Long id) {
        return teamContractService
                .softDelete(id)
                .map(teamContractDto -> new ResponseEntity<>(teamContractDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
