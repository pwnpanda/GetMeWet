package com.example.getmewet.repositories;

import com.example.getmewet.models.Plant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlantService extends CrudRepository<Plant, Integer> {

    List<Plant> getPlants();
    Plant findById(long id);
    Plant findByName(String name);
    boolean createPlant(Plant plant);
    boolean updatePlant(Plant plant);
    boolean removePlant(Plant plant);
    boolean isPlantExist(Plant plant);

}
