package com.fedeavi.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fedeavi.rest.client.ClientRestTutorial;
import com.fedeavi.rest.client.TutorialPojo;
import com.fedeavi.rest.main.RestUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialRestController {

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        ClientRestTutorial client = new ClientRestTutorial();
        List<TutorialPojo> tutorialList = client.getAllTutorials();
        List<Tutorial> tutorials = new ArrayList<Tutorial>();
        for(TutorialPojo pojo: tutorialList){
            Tutorial tutorial = new Tutorial();
            tutorial.setDescription(pojo.getDescription());
            tutorial.setTitle(pojo.getTitle());
            tutorial.setPublished(pojo.isPublished());
            tutorials.add(tutorial);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);

    }

    @PostMapping("/ok")
    public Response getHello(){
        return Response.ok().build();

    }

  

}
