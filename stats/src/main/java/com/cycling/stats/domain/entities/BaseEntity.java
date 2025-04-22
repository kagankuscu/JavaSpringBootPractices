package com.cycling.stats.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity {

    @Column(
            nullable = false,
            updatable = false
    )
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean deleted = false;
}
