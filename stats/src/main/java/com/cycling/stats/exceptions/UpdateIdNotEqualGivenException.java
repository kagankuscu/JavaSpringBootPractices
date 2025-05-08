package com.cycling.stats.exceptions;

public class UpdateIdNotEqualGivenException extends RuntimeException{
    public UpdateIdNotEqualGivenException(Long id, Long givenId) {
        super(String.format("%d-%d", id, givenId));
    }
}
