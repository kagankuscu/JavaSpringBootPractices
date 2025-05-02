package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.teamContractDtos.AddTeamContractDto;
import com.cycling.stats.domain.dtos.teamContractDtos.GetTeamContractDto;
import com.cycling.stats.domain.dtos.teamContractDtos.UpdateTeamContractDto;
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
    public List<GetTeamContractDto> findAll() {
        return teamContractService.findAll();
    }

    @GetMapping(path = "/teamcontract/{id}")
    public ResponseEntity<GetTeamContractDto> findById(@PathVariable("id") Long id) {
        return teamContractService
                .findById(id)
                .map(teamContract -> { return new ResponseEntity<>(teamContract, HttpStatus.OK); })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/teamcontract")
    public ResponseEntity<GetTeamContractDto> create(@RequestBody AddTeamContractDto addTeamContractDto) {
        return new ResponseEntity<>(teamContractService.create(addTeamContractDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/teamcontract/list")
    public ResponseEntity<List<GetTeamContractDto>> create(@RequestBody List<AddTeamContractDto> addTeamContractDtos) {
        return new ResponseEntity<>(teamContractService.createList(addTeamContractDtos), HttpStatus.CREATED);
    }

    @PutMapping(path = "/teamcontract/{id}")
    public ResponseEntity<GetTeamContractDto> update(@PathVariable("id") Long id,
                                                     @RequestBody UpdateTeamContractDto updateTeamContractDto) {
        return teamContractService
                .update(id, updateTeamContractDto)
                .map(teamContract -> new ResponseEntity<>(teamContract, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/teamcontract/{id}")
    public ResponseEntity<GetTeamContractDto> partialUpdate(@PathVariable("id") Long id,
                                                            @RequestBody UpdateTeamContractDto updateTeamContractDto) {
        return teamContractService
                .partialUpdate(id, updateTeamContractDto)
                .map(teamContract -> new ResponseEntity<>(teamContract, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/teamcontract/{id}")
    public ResponseEntity<GetTeamContractDto> delete(@PathVariable("id") Long id) {
        if (!teamContractService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/teamcontract/{id}/delete")
    public ResponseEntity<GetTeamContractDto> softDelete(@PathVariable("id") Long id) {
        return teamContractService
                .softDelete(id)
                .map(teamContractDto -> new ResponseEntity<>(teamContractDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
