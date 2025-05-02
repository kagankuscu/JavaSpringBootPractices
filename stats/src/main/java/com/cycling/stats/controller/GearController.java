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
@RequestMapping(path = "${api.prefix}")
public class GearController {
    private final GearService gearService;

    @GetMapping(path = "/gears")
    public ApiResponse findAll() {
        return new ApiResponse("success", gearService.findAll());
    }

    @GetMapping(path = "/gears/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(new ApiResponse("success", gearService.findById(id)), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/gears")
    public ResponseEntity<ApiResponse> create(@RequestBody AddGearDto gearDto) {
        try {
            return new ResponseEntity<>(new ApiResponse("success", gearService.create(gearDto)),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/gears/list")
    public ResponseEntity<ApiResponse> create(@RequestBody List<AddGearDto> gearDtos) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", gearService.createList(gearDtos)),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/gears/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                             @RequestBody UpdateGearDto updateGearDto) {
        try {
            return new ResponseEntity<>(new ApiResponse("success", gearService.update(id, updateGearDto)), HttpStatus.OK);
        } catch (ResourceNotFoundException | UpdateIdNotEqualGivenException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "/gears/{id}")
    public ResponseEntity<ApiResponse> partialUpdate(@PathVariable("id") Long id,
                                                    @RequestBody UpdateGearDto updateGearDto) {
        try {
            return new ResponseEntity<>(new ApiResponse("success", gearService.partialUpdate(id, updateGearDto)), HttpStatus.OK);
        } catch (ResourceNotFoundException | UpdateIdNotEqualGivenException e) {
           return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/gears/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        try {
            gearService.delete(id);
            return new ResponseEntity<>(new ApiResponse("success", null), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/gears/{id}/delete")
    public ResponseEntity<ApiResponse> softDelete(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(new ApiResponse("successs",gearService.softDelete(id)), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }
}
