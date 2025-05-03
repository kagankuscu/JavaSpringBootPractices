package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.riderDtos.AddRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.GetRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.UpdateRiderDto;

import java.util.List;
import java.util.Optional;

public interface RiderService {

    List<GetRiderDto> findAll();

    GetRiderDto findById(Long id);

    GetRiderDto create(AddRiderDto riderDto);

    List<GetRiderDto> createList(List<AddRiderDto> riderDtos);

    GetRiderDto update(Long id, UpdateRiderDto updateRiderDto);

    GetRiderDto partialUpdate(Long id, UpdateRiderDto updateRiderDto);

    void delete(Long id);

    GetRiderDto softDelete(Long id);
}
