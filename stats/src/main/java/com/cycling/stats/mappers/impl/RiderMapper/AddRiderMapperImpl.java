package com.cycling.stats.mappers.impl.RiderMapper;

import com.cycling.stats.domain.dtos.riderDtos.AddRiderDto;
import com.cycling.stats.domain.entities.Rider;
import com.cycling.stats.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddRiderMapperImpl implements Mapper<Rider, AddRiderDto> {

    private ModelMapper modelMapper;

    @Override
    public Rider mapTo(AddRiderDto addRiderDto) {
        return modelMapper.map(addRiderDto, Rider.class);
    }

    @Override
    public AddRiderDto mapFrom(Rider rider) {
        return modelMapper.map(rider, AddRiderDto.class);
    }
}
