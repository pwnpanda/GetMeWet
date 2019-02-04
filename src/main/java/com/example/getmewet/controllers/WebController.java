package com.example.getmewet.controllers;

import com.example.getmewet.models.Plant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/")
public class WebController {
    private static final String template = "Plant %s!";
    private final AtomicLong counter = new AtomicLong();

    // / gives login page
    @RequestMapping(method = GET)
    public String getBase(){
        return "base";
    }

    // /today.html gives today's status
    @RequestMapping(value = "/today", method = GET)
    public String getToday(){
        return "today";
    }

    // /update allows setting today's status (switches for yes/no and save button)
    @RequestMapping(value = "/update", method = GET)
    public String getUpdate(){
        return "update";
    }

    // /listPlants shows all plants (names - links to that plants ID)
    @RequestMapping(value = "/listplants", method = GET)
    public String getAllPlants(){
        return "listplants";
    }

    // /plants/{id} shows data about that plant
    @RequestMapping(value = "/webPlant", method = GET)
    public String getThisPlant(){
        // ???? TODO
        return "plants/";
    }

    // /status show this months status for all plants (day + red dots + green dots)
    @RequestMapping(value = "/status", method = GET)
    public String getStatus(){
        return "status";
    }

    // /addPlant adds another plant to the db (post info and user cred - API!)
    @RequestMapping(value = "/addPlant", method = GET)
    public String addPlant(){
        return "addPlant";
    }

    // /removePlant removes the selected plant from the db (post id & user cred - API!)
    @RequestMapping(value = "/removePlant", method = GET)
    public String removePlant(){
        return "removePlant";
    }

    // /updatePlant updates the selected plant (post info and user cred- API!)
    @RequestMapping(value = "/updatePlant", method = GET)
    public String updatePlant(){
        return "updatePlant";
    }

    // /logout logs out and returns to /
    @RequestMapping(value = "/logout", method = GET)
    public String logout(){
        return "logout";
    }

}

