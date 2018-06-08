package com.saladilb.controllers;


import com.saladilb.entities.City;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {

    public void run() {
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(new City(2500000, "Воронеж"));
        cities.add(new City(3000000, "Казань"));
        cities.add(new City(10000000, "Москва"));
        cities.add(new City(5000000, "Питер"));
        cities.add(new City(1000000, "Волжск"));

        List<String> names = cities.stream()
                .filter(e -> e.getPopulation() > 1_000_000)
                .map(e->e.geName())
                .collect(Collectors.toList());

        for (int i = 0; i < names.size(); i ++) {
            System.out.println(names.get(i));
        }
    }
}
