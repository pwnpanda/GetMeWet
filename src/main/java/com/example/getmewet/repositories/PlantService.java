package com.example.getmewet.repositories;

import com.example.getmewet.models.Plant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlantService extends CrudRepository<Plant, Integer> {

    List<Plant> getPlants();
    Plant findById(int id);
    Plant findByName(String name);
    void createPlant(Plant plant);
    void updatePlant(Plant plant);
    //void removePlant(Plant plant);
    void deletePlantById(int id);
    boolean existsPlantById(int id);
    //boolean isPlantExist(Plant plant);

}
