package com.example.getmewet.repositories;

import com.example.getmewet.models.Plant;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    public void createPlant(Plant plant){
        plant.setId(counter.incrementAndGet());
        plants.add(plant);
    }

    public void updatePlant(Plant plant){
        int index = plants.indexOf(plant);
        plants.set(index, plant);
    }

    public void deletePlantById(int id){
        for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
            Plant plant = iterator.next();
            if (plant.getId() == id) {
                iterator.remove();
            }
        }
    }

    boolean existsPlantById(int id){
        return findById(id) != null;
    }

    /*public void removePlant(Plant plant){
        for (Iterator<Plant> iterator = plants.iterator(); iterator.hasNext();) {
            Plant compare = iterator.next();
            if (plant == compare) {
                iterator.remove();
            }
        }
    }

    boolean isPlantExist(Plant plant){
        return findByName(plant.getName()) != null;
    }

    Plant save(Plant plant){
        if (plants.contains(plant)) updatePlant(plant);
        else createPlant(plant);
        return plant;
    }


    private static List<Plant> populate_Plants(){
        List<Plant> plants = new ArrayList<Plant>();
        plants.add(new Plant("TestPlant1", "NoPic"));
        return plants;
    }*/
}
