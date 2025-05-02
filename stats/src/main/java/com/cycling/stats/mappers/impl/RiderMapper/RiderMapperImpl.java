package com.cycling.stats.mappers.impl.RiderMapper;

import com.cycling.stats.domain.dtos.riderDtos.GetRiderDto;
import com.cycling.stats.domain.entities.Rider;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RiderMapperImpl implements Mapper<Rider, GetRiderDto> {

    private final ModelMapper modelMapper;

    @Override
    public Rider mapTo(GetRiderDto getRiderDto) {
        return modelMapper.map(getRiderDto, Rider.class);
    }

    @Override
    public GetRiderDto mapFrom(Rider rider) {
        return modelMapper.map(rider, GetRiderDto.class);
    }
}
