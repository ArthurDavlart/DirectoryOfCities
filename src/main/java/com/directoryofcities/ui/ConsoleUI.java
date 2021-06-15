package com.directoryofcities.ui;

import com.directoryofcities.common.City;
import com.directoryofcities.services.CityService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {
    interface Command{
        String DISPLAY = "display";
        String HELP = "help";
        String EXIT = "exit";
        String DISPLAYSORTEDCITIES = "displaysortedcities";
        String DISPLAYMAXELEMENT = "displaymaxelement";
        String GROUPCITIESBYREGION = "groupcitiesbyregion";
    }

    private boolean isAlive;
    private final CityService cityService;

    public ConsoleUI(CityService cityService) {
        this.cityService = cityService;
    }

    public void menu(){
        this.isAlive = true;
        Scanner scanner = new Scanner(System.in);
        while (this.isAlive){
            switch (scanner.nextLine().toLowerCase()){
                case Command.DISPLAY:
                    displayAllCities();
                    break;
                case Command.DISPLAYSORTEDCITIES:
                    displaySortedCities();
                    break;
                case Command.DISPLAYMAXELEMENT:
                    displayMaxElement();
                    break;
                case Command.GROUPCITIESBYREGION:
                    displayGroupCitiesByRegion();
                    break;
                case Command.HELP:
                    help();
                    break;
                case Command.EXIT:
                    exit();
                    break;
                default: System.out.println("Я не знаю этой команды. " +
                        "Используйте команду help, " +
                        "что бы посмотреть все возможные команды");
                    break;
            }
        }

    }

    private void displayAllCities(){
        display(cityService.getAll());
    }

    private void displaySortedCities(){
        List<City> cities = cityService.getAll();

        cityService.sortByName(cities);
        display(cities);

        cityService.sortByDistrict(cities);
        display(cities);
    }

    private void display(List<City> cities){
        cities.stream().forEach(city -> System.out.println(city.toString()));
        System.out.println();
    }

    private void displayMaxElement(){
        City city = cityService.findCityWithMaxPopulation();

        System.out.println(String.format("[%d] = %d", city.getId(), city.getPopulation()));
    }

    private void displayGroupCitiesByRegion(){
        Map<String, List<City>> groupedCities = cityService.groupCitiesByRegion();

        groupedCities
                .forEach((region, cities) -> System.out.println(String.format("%s - %d", region, cities.size())));
    }

    private void help(){
        String helpStr = "display - вывести все города в справочнике;\n" +
                "exit - команда завершения программы;\n" +
                "displaysortedcities - команда показа отсортированных городов;" +
                "displaymaxelement - вывод города с самым большим количество насиления;" +
                "groupcitiesbyregion - вывод количество городов сгрупированных по регионам." ;
        System.out.println(helpStr);
    }

    private void exit(){
        System.out.println("Спасибо, что использовали наше приложение!");
        this.isAlive = false;
    }
}
