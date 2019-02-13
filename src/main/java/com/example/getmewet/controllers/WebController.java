package com.example.getmewet.controllers;

import com.example.getmewet.models.Status;
import com.example.getmewet.models.User;
import com.example.getmewet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/")
public class WebController {
    private final AtomicLong counter = new AtomicLong();
    private static final String url = "http://localhost:9090/api";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserService userService;

    // / gives base page
    @RequestMapping(method = GET)
    public String getBase() {
        return "index";
    }

    private String token;

    private HttpEntity<String> createAuth() {
        HttpHeaders auth = new HttpHeaders();
        auth.add(HttpHeaders.AUTHORIZATION, token);
        return new HttpEntity<String>("parameters", auth);
    }

    // /login page
    @RequestMapping(value = "/login", method = GET)
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("login");
        return modelAndView;
    }


    // TODO need error handling for 401
    // /login page
    @RequestMapping(value = "/login", method = POST)
    public ModelAndView postLogin(@Valid User user, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();

        // Create post request
        HttpHeaders head = new HttpHeaders();
        head.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", user.getUsername());
        map.add("password", user.getPassword());
        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(map, head);
        RestTemplate tmp = new RestTemplate();
        //Do post
        ResponseEntity<String> res = tmp.postForEntity("http://localhost:9090/login", user, String.class);
        System.out.println(res);

        if (res.getStatusCode() != HttpStatus.OK) {
            model.addObject("message", "Error! Please try again!");
            model.addObject("user", new User());
            model.setViewName("login");
            return model;
        }

        HttpHeaders headers = res.getHeaders();
        token = headers.getFirst(headers.AUTHORIZATION);

        model.setViewName("today");
        System.out.println(token);
        response.setHeader(HttpHeaders.AUTHORIZATION, token);

        return getToday();
    }

    @RequestMapping(value = "/register", method = GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("reg");
        return modelAndView;
    }

    // /reg.html post registers user
    @RequestMapping(value = "/register", method = POST)
    public ModelAndView getReg(@Valid User user) {
        System.out.println("PW " + user.getPassword());
        RestTemplate tmp = new RestTemplate();
        User res = tmp.postForObject(url + "/user/register", user, User.class);

        ModelAndView model = new ModelAndView();

        System.out.println(res);
        if (res == null) {
            model.addObject("message", "User registration failed!");
            model.addObject("user", new User());
            model.setViewName("reg");
            return model;
        }
        model.addObject("user", new User());
        model.setViewName("login");
        return model;
    }

    // /User.html gives info about a user
    @RequestMapping(value = "/user/{id}", method = GET)
    public String getUser(@PathVariable int id, Model model) {
        RestTemplate tmp = new RestTemplate();
        HttpEntity<String> ent = createAuth();
        ResponseEntity<User> res = tmp.exchange(url + "/user/" + id, HttpMethod.GET, ent, User.class);
        System.out.println(res);
        User us = res.getBody();
        System.out.println(us);
        if (us != null) model.addAttribute("users", us);
        return "user";
    }

    // /Users.html gives info about a user
    @RequestMapping(value = "/users", method = GET)
    public String getUsers(Model model) {
        RestTemplate tmp = new RestTemplate();
        HttpEntity<String> ent = createAuth();
        ResponseEntity<String> res = tmp.exchange(url + "users", HttpMethod.GET, ent, String.class);
        System.out.println(res);
        // How to parse list of users? TODO
        User us = null;
        //List<User> us = (List<User>) res.getBody();
        System.out.println(us);
        if (us != null) model.addAttribute("users", us);
        return "users";
    }

    // /today shows today's status
    @RequestMapping(value = "/today", method = GET)
    public ModelAndView getToday() {
        ModelAndView model = new ModelAndView();
        RestTemplate tmp = new RestTemplate();
        HttpEntity<String> ent = createAuth();
        LocalDate date = LocalDate.now();
        System.out.println(date);
        String req = url + "/status/day/" + date.toString();
        //System.out.println(req);
        ResponseEntity<List<Status>> statuses = tmp.exchange(req, HttpMethod.GET, ent, new ParameterizedTypeReference<List<Status>>() {
        });

        model.addObject("statuses", statuses);
        model.setViewName("today");
        return model;
    }

    // /update allows setting today's status (switches for yes/no and save button)
    @RequestMapping(value = "/update", method = GET)
    public String getUpdate() {
        return "update";
    }

    // /listPlants shows all plants (names - links to that plants ID)
    @RequestMapping(value = "/listplants", method = GET)
    public String getAllPlants() {
        return "listplants";
    }

    // /plants/{id} shows data about that plant - Allow for updating fields using API!
    @RequestMapping(value = "/webPlant", method = GET)
    public String getThisPlant() {
        // ???? TODO
        return "plants/";
    }

    // /status show this months status for all plants (day + red dots + green dots)
    @RequestMapping(value = "/status", method = GET)
    public String getStatus() {
        return "status";
    }

    // /addPlant adds another plant to the db (post info and user cred - API!)
    @RequestMapping(value = "/addPlant", method = GET)
    public String addPlant() {
        return "addplant";
    }

    // /removePlant removes the selected plant from the db (post id & user cred - API!)
    @RequestMapping(value = "/removePlant", method = GET)
    public String removePlant() {
        return "removeplant";
    }

    // /logout logs out and returns to /
    @RequestMapping(value = "/logout", method = POST)
    public String logout() {
        token = null;
        return "logout";
    }

}

