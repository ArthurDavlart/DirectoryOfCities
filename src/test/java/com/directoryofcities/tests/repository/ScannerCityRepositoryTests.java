package com.directoryofcities.tests.repository;

import com.directoryofcities.common.City;
import com.directoryofcities.dal.ScannerCityRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScannerCityRepositoryTests {
    private final ScannerCityRepositoryImpl scannerCityRepository =
            new ScannerCityRepositoryImpl("src/test/java/com/directoryofcities/tests/repository/dirictoryofcitytestdb.txt",null);

    @Test
    void getAll_DontHaveCities_HaveCities(){
        List<City> expectedCities = new LinkedList<>();
        expectedCities.add(new City(1, "Майкоп", "Адыгея",  "Южный", 144246, 1857));

        List<City> actualCities = scannerCityRepository.getAll();

        assertTrue(actualCities.get(0).equals(expectedCities.get(0)), String.format("expected: %s\nactual: %s", expectedCities.get(0).toString(),
                actualCities.get(0).toString()));
    }
}
