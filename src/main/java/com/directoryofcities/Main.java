package com.directoryofcities;

import com.directoryofcities.common.ConsoleLoggerImpl;
import com.directoryofcities.common.Logger;
import com.directoryofcities.dal.CityRepository;
import com.directoryofcities.dal.ScannerCityRepositoryImpl;
import com.directoryofcities.services.CityService;
import com.directoryofcities.services.CityServiceImpl;
import com.directoryofcities.ui.ConsoleUI;

public class Main {
    private static ConsoleUI consoleUI;

    public static void main(String[] args) {
        init();
        start();
    }

    private static void init(){
        Logger logger = new ConsoleLoggerImpl();
        CityRepository cityRepository =
                new ScannerCityRepositoryImpl("src/main/resources/dirictoryofcitiesDB.txt", logger);
        CityService cityService = new CityServiceImpl(cityRepository);

        consoleUI = new ConsoleUI(cityService);
    }

    private static void start(){
        consoleUI.menu();
    }
}
