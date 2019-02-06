package com.example.getmewet.controllers;

import com.example.getmewet.models.User;
import com.example.getmewet.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class APIUser {

    @Autowired
    UserService userService;


    public static final Logger logger = LoggerFactory.getLogger(APIUser.class);

    @GetMapping(value = "/all")
    public ResponseEntity<List<String>> listAllUsers(){
        logger.info("Get all users from API!");
        List<User> users = userService.findAll();
        if (users.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        List<String> data = new ArrayList<String>();
        for (User user: users){
            data.add(user.toString());
        }
        return new ResponseEntity<List<String>>(data, HttpStatus.OK);
    }
}
