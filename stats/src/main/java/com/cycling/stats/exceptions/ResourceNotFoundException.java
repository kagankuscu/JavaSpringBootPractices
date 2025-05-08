package com.cycling.stats.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String type, Long id) {
        super(String.format("%s-%d", type, id));
    }

    public ResourceNotFoundException(String type, Integer id) {
        super(String.format("%s-%d", type, id));
    }
}
