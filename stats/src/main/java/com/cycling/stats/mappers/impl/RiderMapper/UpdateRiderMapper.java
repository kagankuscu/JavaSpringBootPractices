package com.cycling.stats.mappers.impl.RiderMapper;

import com.cycling.stats.domain.dtos.riderDtos.UpdateRiderDto;
import com.cycling.stats.domain.entities.Rider;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateRiderMapper implements Mapper<Rider, UpdateRiderDto> {

    private ModelMapper modelMapper;

    @Override
    public Rider mapTo(UpdateRiderDto updateRiderDto) {
        return modelMapper.map(updateRiderDto, Rider.class);
    }

    @Override
    public UpdateRiderDto mapFrom(Rider rider) {
        return modelMapper.map(rider, UpdateRiderDto.class);
    }
}
