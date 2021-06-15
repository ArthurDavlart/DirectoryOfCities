package com.directoryofcities.services;

import com.directoryofcities.common.City;

import java.util.List;
import java.util.Map;

public interface CityService {
    List<City> getAll();
    void sortByName(List<City> cities);
    void sortByDistrict(List<City> cities);
    City findCityWithMaxPopulation();
    Map<String, List<City>> groupCitiesByRegion();
}
