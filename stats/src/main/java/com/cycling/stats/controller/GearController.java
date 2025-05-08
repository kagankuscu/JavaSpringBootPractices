package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.gearDtos.AddGearDto;
import com.cycling.stats.domain.dtos.gearDtos.GetGearDto;
import com.cycling.stats.domain.dtos.gearDtos.UpdateGearDto;
import com.cycling.stats.exceptions.ResourceNotFoundException;
import com.cycling.stats.exceptions.UpdateIdNotEqualGivenException;
import com.cycling.stats.response.ApiResponse;
import com.cycling.stats.services.GearService;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "${api.prefix}/gears")
public class GearController {
    private final GearService gearService;

    @GetMapping(path = "")
    public ApiResponse findAll() {
        return new ApiResponse("success", gearService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new ApiResponse("success", gearService.findById(id)), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse> create(@RequestBody AddGearDto gearDto) {
        return new ResponseEntity<>(new ApiResponse("success", gearService.create(gearDto)),
                HttpStatus.CREATED);
    }

    @PostMapping(path = "/list")
    public ResponseEntity<ApiResponse> create(@RequestBody List<AddGearDto> gearDtos) {
        return new ResponseEntity<>(
                new ApiResponse("success", gearService.createList(gearDtos)),
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                             @RequestBody UpdateGearDto updateGearDto) {
        return new ResponseEntity<>(
                new ApiResponse("success", gearService.update(id, updateGearDto)), HttpStatus.OK
        );
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> partialUpdate(@PathVariable("id") Long id,
                                                    @RequestBody UpdateGearDto updateGearDto) {
        return new ResponseEntity<>(
                new ApiResponse("success", gearService.partialUpdate(id, updateGearDto)), HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
            gearService.delete(id);
        return new ResponseEntity<>(new ApiResponse("success", null), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/delete")
    public ResponseEntity<ApiResponse> softDelete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new ApiResponse("successs",gearService.softDelete(id)), HttpStatus.OK);
    }
}
