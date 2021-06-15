package com.directoryofcities.dal;

import com.directoryofcities.common.City;

import java.util.List;

public interface CityRepository {
    List<City> getAll();
}
