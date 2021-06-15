package com.directoryofcities.services;

import com.directoryofcities.common.City;
import com.directoryofcities.dal.CityRepository;

import java.util.*;
import java.util.stream.Collectors;

public class CityServiceImpl implements CityService{
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAll() {
        return cityRepository.getAll();
    }

    @Override
    public void sortByName(List<City> cities) {
        cities.sort(Comparator.comparing(c -> c.getName().toLowerCase()));
    }

    @Override
    public void sortByDistrict(List<City> cities) {
        cities.sort(Comparator.comparing(c -> c.getDistrict().toLowerCase().concat(c.getName().toLowerCase(Locale.ROOT))));
    }

    @Override
    public City findCityWithMaxPopulation() {
        List<City> cities = cityRepository.getAll();

        return cities.stream().max(Comparator.comparing(city -> city.getPopulation())).get();
    }

    @Override
    public Map<String, List<City>> groupCitiesByRegion() {
        List<City> cities = cityRepository.getAll();

        return cities
                .stream()
                .collect(Collectors.groupingBy(c -> c.getRegion()));
    }
}
