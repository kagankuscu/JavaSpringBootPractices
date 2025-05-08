package com.cycling.stats.controller;

import com.cycling.stats.domain.dtos.riderDtos.AddRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.GetRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.UpdateRiderDto;
import com.cycling.stats.exceptions.ResourceNotFoundException;
import com.cycling.stats.exceptions.UpdateIdNotEqualGivenException;
import com.cycling.stats.response.ApiResponse;
import com.cycling.stats.services.RiderService;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "${api.prefix}/riders")
public class RiderController {
    private final RiderService riderService;

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse> findAll() {
        return new ResponseEntity<>(new ApiResponse("success", riderService.findAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id) {
       return new ResponseEntity<>(new ApiResponse("success", riderService.findById(id)), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse> create(@RequestBody AddRiderDto riderDto) {
        return new ResponseEntity<>(new ApiResponse("success", riderService.create(riderDto)),
                HttpStatus.CREATED);
    }

    @PostMapping(path = "/list")
    public ResponseEntity<ApiResponse> create(@RequestBody List<AddRiderDto> riderDtos) {
        return new ResponseEntity<>(new ApiResponse("success", riderService.createList(riderDtos)),
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                              @RequestBody UpdateRiderDto updateRiderDto) {
        return new ResponseEntity<>(
                new ApiResponse("success", riderService.update(id, updateRiderDto)), HttpStatus.OK
        );
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> partialUpdate(@PathVariable("id") Long id,
                                                     @RequestBody UpdateRiderDto updateRiderDto) {
        return new ResponseEntity<>(
                new ApiResponse("success", riderService.partialUpdate(id, updateRiderDto)),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
            riderService.delete(id);
        return new ResponseEntity<>(
                new ApiResponse("success", null),
                HttpStatus.OK
        );
    }

    @PutMapping(path = "/{id}/delete")
    public ResponseEntity<ApiResponse> softDelete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                new ApiResponse("success", riderService.softDelete(id)),
                HttpStatus.OK
        );
    }
}
