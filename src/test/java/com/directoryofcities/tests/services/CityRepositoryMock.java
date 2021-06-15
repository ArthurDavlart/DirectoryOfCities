package com.directoryofcities.tests.services;

import com.directoryofcities.common.City;
import com.directoryofcities.dal.CityRepository;

import java.util.List;

public class CityRepositoryMock implements CityRepository {
    private List<City> cities;

    @Override
    public List<City> getAll() {
        return cities;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
