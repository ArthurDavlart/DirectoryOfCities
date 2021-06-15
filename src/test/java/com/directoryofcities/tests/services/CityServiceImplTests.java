package com.directoryofcities.tests.services;

import com.directoryofcities.common.City;
import com.directoryofcities.services.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityServiceImplTests {
    private final CityRepositoryMock cityRepositoryMock = new CityRepositoryMock();
    private final CityServiceImpl cityService = new CityServiceImpl(cityRepositoryMock);

    @BeforeEach
    void init() {
        City c1 = new City(1, "Б", "А",  "Б", 2, 1);
        City c2 = new City(2, "В", "Б",  "А", 3, 1);
        City c3 = new City(3, "А", "Б",  "Б", 1, 1);
        City c4 = new City(4, "Г", "А",  "А", 4, 1);
        List<City> cities = new LinkedList(){{
            add(c1);add(c2);add(c3);add(c4);
        }};

        cityRepositoryMock.setCities(cities);
    }

    @Test
    void sortByName_HaveNotSortedList_HaveSortedList(){
        City c1 = new City(1, "Майкоп", "Адыгея",  "Южный", 144246, 1857);
        City c2 = new City(2, "Адыгейск", "Адыгея",  "Южный", 12248, 1973);
        List<City> notSortedList = new LinkedList(){{
            add(c1);
            add(c2);
        }};
        List<City> expectedCities = new LinkedList(){{
            add(c2);
            add(c1);
        }};

        cityService.sortByName(notSortedList);

        assertAll(
                () -> assertEquals(expectedCities.get(0), notSortedList.get(0)),
                () -> assertEquals(expectedCities.get(1), notSortedList.get(1))
        );
    }

    @Test
    void sortByDistrict_HaveNotSortedList_HaveSortedList(){
        City c1 = new City(1, "Б", "А",  "Б", 1, 1);
        City c2 = new City(2, "В", "Б",  "А", 1, 1);
        City c3 = new City(3, "А", "Б",  "Б", 1, 1);
        City c4 = new City(4, "Г", "А",  "А", 1, 1);
        List<City> notSortedList = new LinkedList(){{
            add(c1);add(c2);add(c3);add(c4);
        }};
        List<City> expectedCities = new LinkedList(){{
            add(c2);add(c4);add(c3);add(c1);
        }};

        cityService.sortByDistrict(notSortedList);

        assertAll(
                () -> assertEquals(expectedCities.get(0), notSortedList.get(0)),
                () -> assertEquals(expectedCities.get(1), notSortedList.get(1)),
                () -> assertEquals(expectedCities.get(2), notSortedList.get(2)),
                () -> assertEquals(expectedCities.get(3), notSortedList.get(3))
        );
    }

    @Test
    void findCityWithMaxPopulation_HaveNotCity_HaveCityWithMaxPopulation(){
        City expected = cityRepositoryMock.getCities().stream().max(Comparator.comparing(city -> city.getPopulation())).get();

        City actual = cityService.findCityWithMaxPopulation();

        assertEquals(expected, actual);
    }

    @Test
    void groupCitiesByRegion_HaveNotGroupedCities_HaveGroupedCitiesByRegion(){
        Map<String, List<City>> expected = cityRepositoryMock.getCities()
                .stream()
                .collect(Collectors.groupingBy(c -> c.getRegion()));

        Map<String, List<City>> actual = cityService.groupCitiesByRegion();

        expected.forEach((region, cities) -> assertEquals(cities.size(), actual.get(region).size()));
    }


}
