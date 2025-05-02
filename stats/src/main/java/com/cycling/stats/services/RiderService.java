package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.riderDtos.AddRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.GetRiderDto;
import com.cycling.stats.domain.dtos.riderDtos.UpdateRiderDto;

import java.util.List;
import java.util.Optional;

public interface RiderService {

    List<GetRiderDto> findAll();

    Optional<GetRiderDto> findById(Long id);

    GetRiderDto create(AddRiderDto riderDto);

    List<GetRiderDto> createList(List<AddRiderDto> riderDtos);

    Optional<GetRiderDto> update(Long id, UpdateRiderDto updateRiderDto);

    Optional<GetRiderDto> partialUpdate(Long id, UpdateRiderDto updateRiderDto);

    boolean delete(Long id);

    Optional<GetRiderDto> softDelete(Long id);
}
