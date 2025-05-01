package com.cycling.stats.domain.dtos.riderDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddRiderDto {
    private String fullName;
    private Date birthdate;
    private String nationality;
    private Long weight;
    private Long height;
    private Long teamId;
}
