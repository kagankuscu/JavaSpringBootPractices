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
@RequestMapping(path = "${api.prefix}")
public class RiderController {
    private final RiderService riderService;

    @GetMapping(path = "/riders")
    public ResponseEntity<ApiResponse> findAll() {
        return new ResponseEntity<>(new ApiResponse("success", riderService.findAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/riders/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id) {
        try {
           return new ResponseEntity<>(new ApiResponse("success", riderService.findById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/riders")
    public ResponseEntity<ApiResponse> create(@RequestBody AddRiderDto riderDto) {
        try {
            return new ResponseEntity<>(new ApiResponse("success", riderService.create(riderDto)),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/riders/list")
    public ResponseEntity<ApiResponse> create(@RequestBody List<AddRiderDto> riderDtos) {
        try {
            return new ResponseEntity<>(new ApiResponse("success", riderService.createList(riderDtos)),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/riders/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                              @RequestBody UpdateRiderDto updateRiderDto) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", riderService.update(id, updateRiderDto)), HttpStatus.OK
            );
        } catch (ResourceNotFoundException | UpdateIdNotEqualGivenException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "/riders/{id}")
    public ResponseEntity<ApiResponse> partialUpdate(@PathVariable("id") Long id,
                                                     @RequestBody UpdateRiderDto updateRiderDto) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", riderService.partialUpdate(id, updateRiderDto)),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException | UpdateIdNotEqualGivenException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/riders/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        try {
            riderService.delete(id);
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

    @PutMapping(path = "/riders/{id}/delete")
    public ResponseEntity<ApiResponse> softDelete(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse("success", riderService.softDelete(id)),
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
