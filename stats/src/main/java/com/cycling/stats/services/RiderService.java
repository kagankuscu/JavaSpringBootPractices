package com.cycling.stats.services;

import com.cycling.stats.domain.dtos.RiderDto;

import java.util.List;
import java.util.Optional;

public interface RiderService {

    List<RiderDto> findAll();

    Optional<RiderDto> findById(Long id);

    RiderDto create(RiderDto riderDto);

    List<RiderDto> createList(List<RiderDto> riderDtos);

    Optional<RiderDto> update(Long id, RiderDto riderDto);

    Optional<RiderDto> partialUpdate(Long id, RiderDto riderDto);

    boolean delete(Long id);

    Optional<RiderDto> softDelete(Long id);
}
