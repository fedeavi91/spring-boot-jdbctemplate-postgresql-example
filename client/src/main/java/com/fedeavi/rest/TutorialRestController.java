package com.fedeavi.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.fedeavi.rest.client.ClientRestTutorial;
import com.fedeavi.rest.client.TutorialPojo;
import com.fedeavi.rest.main.RestUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TutorialRestController {

    public Response getAllTutorials() {
        TutorialResponse response = new TutorialResponse();
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
        response.setTutorials(tutorials);

        return RestUtility.getNewResp(wrapResponse(response));

    }

    private Object wrapResponse(TutorialResponse res) {
        String jsonResponse = "";

        Gson gson = new GsonBuilder().setDateFormat("dd/MM/YYYY").create();
        jsonResponse = gson.toJson(res, TutorialResponse.class);
        return jsonResponse;

    }

}
