package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.gearDtos.AddGearDto;
import com.cycling.stats.domain.dtos.gearDtos.GearDto;
import com.cycling.stats.domain.dtos.teamDtos.AddTeamDto;
import com.cycling.stats.domain.entities.Gear;
import com.cycling.stats.services.GearService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "${api.prefix}")
public class GearController {
    private final GearService gearService;

    @GetMapping(path = "/gears")
    public List<GearDto> findAll() {
        return gearService.findAll();
    }

    @GetMapping(path = "/gears/{id}")
    public ResponseEntity<GearDto> findById(@PathVariable("id") Long id) {
        return gearService
                .findById(id)
                .map(rider -> { return new ResponseEntity<>(rider, HttpStatus.OK); })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/gears")
    public ResponseEntity<GearDto> create(@RequestBody AddGearDto gearDto) {
        return new ResponseEntity<>(gearService.create(gearDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/gears/list")
    public ResponseEntity<List<GearDto>> create(@RequestBody List<AddGearDto> gearDtos) {
        return new ResponseEntity<>(gearService.createList(gearDtos), HttpStatus.CREATED);
    }

    @PutMapping(path = "/gears/{id}")
    public ResponseEntity<GearDto> update(@PathVariable("id") Long id,
                                           @RequestBody GearDto gearDto) {
        return gearService
                .update(id, gearDto)
                .map(rider -> new ResponseEntity<>(rider, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/gears/{id}")
    public ResponseEntity<GearDto> partialUpdate(@PathVariable("id") Long id,
                                                  @RequestBody GearDto gearDto) {
        return gearService
                .partialUpdate(id, gearDto)
                .map(rider -> new ResponseEntity<>(rider, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/gears/{id}")
    public ResponseEntity<GearDto> delete(@PathVariable("id") Long id) {
        if (!gearService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/gears/{id}/delete")
    public ResponseEntity<GearDto> softDelete(@PathVariable("id") Long id) {
        return gearService
                .softDelete(id)
                .map(gearDto -> new ResponseEntity<>(gearDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
