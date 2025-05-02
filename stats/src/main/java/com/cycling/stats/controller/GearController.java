package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.gearDtos.AddGearDto;
import com.cycling.stats.domain.dtos.gearDtos.GetGearDto;
import com.cycling.stats.domain.dtos.gearDtos.UpdateGearDto;
import com.cycling.stats.services.GearService;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
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
    public List<GetGearDto> findAll() {
        return gearService.findAll();
    }

    @GetMapping(path = "/gears/{id}")
    public ResponseEntity<GetGearDto> findById(@PathVariable("id") Long id) {
        return gearService
                .findById(id)
                .map(rider -> { return new ResponseEntity<>(rider, HttpStatus.OK); })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/gears")
    public ResponseEntity<GetGearDto> create(@RequestBody AddGearDto gearDto) {
        return new ResponseEntity<>(gearService.create(gearDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/gears/list")
    public ResponseEntity<List<GetGearDto>> create(@RequestBody List<AddGearDto> gearDtos) {
        return new ResponseEntity<>(gearService.createList(gearDtos), HttpStatus.CREATED);
    }

    @PutMapping(path = "/gears/{id}")
    public ResponseEntity<GetGearDto> update(@PathVariable("id") Long id,
                                             @RequestBody UpdateGearDto updateGearDto) {
        return gearService
                .update(id, updateGearDto)
                .map(rider -> new ResponseEntity<>(rider, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/gears/{id}")
    public ResponseEntity<GetGearDto> partialUpdate(@PathVariable("id") Long id,
                                                    @RequestBody UpdateGearDto updateGearDto) {
        return gearService
                .partialUpdate(id, updateGearDto)
                .map(rider -> new ResponseEntity<>(rider, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/gears/{id}")
    public ResponseEntity<GetGearDto> delete(@PathVariable("id") Long id) {
        if (!gearService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/gears/{id}/delete")
    public ResponseEntity<GetGearDto> softDelete(@PathVariable("id") Long id) {
        return gearService
                .softDelete(id)
                .map(getGearDto -> new ResponseEntity<>(getGearDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
