package com.directoryofcities.dal;

import com.directoryofcities.common.City;
import com.directoryofcities.common.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ScannerCityRepositoryImpl implements CityRepository{
    private final String filePath;
    private final Logger logger;

    public ScannerCityRepositoryImpl(String filePath, Logger logger) {
        this.filePath = filePath;
        this.logger = logger;
    }

    @Override
    public List<City> getAll() {
        return getCitiesFromFile();
    }

    private List<City> getCitiesFromFile(){
        try (Scanner myReader = new Scanner(new File(this.filePath))){
            List<City> cities = new LinkedList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                City city = parseRow(data);
                cities.add(city);
            }

            return cities;
        }
        catch (FileNotFoundException e){
            logger.write(e.getMessage());

            return new LinkedList<>();
        }
    }

    private City parseRow(String cityRow){
        String[] cityStringAtr = cityRow.split(";");
        return new City(Long.parseLong(cityStringAtr[0]),
                cityStringAtr[1],
                cityStringAtr[2],
                cityStringAtr[3],
                Integer.parseInt(cityStringAtr[4]),
                Integer.parseInt(cityStringAtr[5]));
    }
}
