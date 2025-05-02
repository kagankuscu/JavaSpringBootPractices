package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.riderDtos.AddRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.GetRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.UpdateRiderDto;
import com.cycling.stats.services.RiderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "${api.prefix}")
public class RiderController {
    private final RiderService riderService;

    @GetMapping(path = "/riders")
    public List<GetRiderDto> findAll() {
        return riderService.findAll();
    }

    @GetMapping(path = "/riders/{id}")
    public ResponseEntity<GetRiderDto> findById(@PathVariable("id") Long id) {
        return riderService
                .findById(id)
                .map(rider -> { return new ResponseEntity<>(rider, HttpStatus.OK); })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/riders")
    public ResponseEntity<GetRiderDto> create(@RequestBody AddRiderDto riderDto) {
        return new ResponseEntity<>(riderService.create(riderDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/riders/list")
    public ResponseEntity<List<GetRiderDto>> create(@RequestBody List<AddRiderDto> riderDtos) {
        return new ResponseEntity<>(riderService.createList(riderDtos), HttpStatus.CREATED);
    }

    @PutMapping(path = "/riders/{id}")
    public ResponseEntity<GetRiderDto> update(@PathVariable("id") Long id,
                                              @RequestBody UpdateRiderDto updateRiderDto) {
        return riderService
                .update(id, updateRiderDto)
                .map(rider -> new ResponseEntity<>(rider, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/riders/{id}")
    public ResponseEntity<GetRiderDto> partialUpdate(@PathVariable("id") Long id,
                                                     @RequestBody UpdateRiderDto updateRiderDto) {
        return riderService
                .partialUpdate(id, updateRiderDto)
                .map(rider -> new ResponseEntity<>(rider, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/riders/{id}")
    public ResponseEntity<GetRiderDto> delete(@PathVariable("id") Long id) {
        if (!riderService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/riders/{id}/delete")
    public ResponseEntity<GetRiderDto> softDelete(@PathVariable("id") Long id) {
        return riderService
                .softDelete(id)
                .map(riderDto -> new ResponseEntity<>(riderDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
