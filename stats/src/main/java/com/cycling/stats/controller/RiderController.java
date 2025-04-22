package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.RiderDto;
import com.cycling.stats.domain.dtos.TeamDto;
import com.cycling.stats.domain.entities.Rider;
import com.cycling.stats.services.RiderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class RiderController {
    private final RiderService riderService;

    @GetMapping(path = "/riders")
    public List<RiderDto> findAll() {
        return riderService.findAll();
    }

    @GetMapping(path = "/riders/{id}")
    public ResponseEntity<RiderDto> findById(@PathVariable("id") Long id) {
        return riderService
                .findById(id)
                .map(rider -> { return new ResponseEntity<>(rider, HttpStatus.OK); })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/riders")
    public ResponseEntity<RiderDto> create(@RequestBody RiderDto riderDto) {
        return new ResponseEntity<>(riderService.create(riderDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/riders/list")
    public ResponseEntity<List<RiderDto>> create(@RequestBody List<RiderDto> riderDtos) {
        return new ResponseEntity<>(riderService.createList(riderDtos), HttpStatus.CREATED);
    }

    @PutMapping(path = "/riders/{id}")
    public ResponseEntity<RiderDto> update(@PathVariable("id") Long id,
                                           @RequestBody RiderDto riderDto) {
        return riderService
                .update(id, riderDto)
                .map(rider -> new ResponseEntity<>(rider, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/riders/{id}")
    public ResponseEntity<RiderDto> partialUpdate(@PathVariable("id") Long id,
                                                  @RequestBody RiderDto riderDto) {
        return riderService
                .partialUpdate(id, riderDto)
                .map(rider -> new ResponseEntity<>(rider, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/riders/{id}")
    public ResponseEntity<RiderDto> delete(@PathVariable("id") Long id) {
        if (!riderService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/riders/{id}/delete")
    public ResponseEntity<RiderDto> softDelete(@PathVariable("id") Long id) {
        return riderService
                .softDelete(id)
                .map(riderDto -> new ResponseEntity<>(riderDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
