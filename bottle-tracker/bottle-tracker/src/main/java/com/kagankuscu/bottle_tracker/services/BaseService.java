package com.kagankuscu.bottle_tracker.services;

import java.util.List;

public interface BaseService<T, ID> {
    List<T> findAll();
}
