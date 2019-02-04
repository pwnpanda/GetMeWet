package com.example.getmewet.repositories;

import com.example.getmewet.models.Plant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service("PlantService")
public class PlantServiceImpl {

    private static final AtomicInteger counter = new AtomicInteger();

    private static List<Plant> plants;

    /*
    static {
        plants = populate_plants();
    }*/


    public List<Plant> getPlants() {
        return plants;
    }

    public Plant findById(int id){
        for (Plant plant: plants){
            if (plant.getId() == id){
                return plant;
            }
        }
        return null;
    }

    public Plant findByName(String name){
        for (Plant plant: plants){
            if (plant.getName() == name){
                return plant;
            }
        }
        return null;
    }

    void createPlant(Plant plant){
        plant.setId(counter.incrementAndGet());
        plants.add(plant);
    }

    void updatePlant(Plant plant){
        int index = plants.indexOf(plant);
        plants.set(index, plant);
    }

    void removePlant(long id){
        for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
            Plant plant = iterator.next();
            if (plant.getId() == id) {
                iterator.remove();
            }
        }
    }
    boolean isPlantExist(Plant plant){
        return findByName(plant.getName()) != null;
    }

    /*
    private static List<Plant> populate_Plants(){
        List<Plant> plants = new ArrayList<Plant>();
        plants.add(new Plant("TestPlant1", "NoPic"));
        return plants;
    }*/
}
