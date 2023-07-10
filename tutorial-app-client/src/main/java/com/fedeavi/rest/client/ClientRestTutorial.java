package com.fedeavi.rest.client;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import javax.ws.rs.core.Response.Status;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ClientRestTutorial {

    // private ResteasyClient client;
    private RestTemplate restTemplate;

    private String url;

    public ClientRestTutorial() {
        this.url = "http://app-server:8080/api/tutorials";
        restTemplate = new RestTemplate();
    }

    public List<TutorialPojo> getAllTutorials() {

        List<TutorialPojo> listTutorialPojo = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // client = new ResteasyClientBuilder().build();
        // ResteasyWebTarget target = client.target(this.url);
        // Response r = target.request().get();

        ResponseEntity<String> jsonString = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        // String jsonString = r.readEntity(String.class);
        System.out.println("jsonString = " + jsonString);
        System.out.println("jsonString.getBody() = " + jsonString.getBody());
        if (jsonString != null) {
            JsonArray jsonArray = toJsonArray(jsonString.getBody());

            // if (responseJson != null && responseJson.getString("responseStatus").equals(Status.OK.toString())) {
                listTutorialPojo = new ArrayList<TutorialPojo>();
                // JsonArray jsonArray = responseJson.getJsonArray("");
                for (JsonValue value : jsonArray) {
                    JsonObject obj = null;
                    TutorialPojo tutorialPojo = new TutorialPojo();
                    if (value.getValueType() == JsonValue.ValueType.OBJECT)
                        obj = (JsonObject) value;

                    tutorialPojo.setTitle(obj.getString(TutorialPojo.TITLE));
                    tutorialPojo.setDescription(obj.getString(TutorialPojo.DESCRIPTION));
                    tutorialPojo.setPublished(obj.getBoolean(TutorialPojo.PUBLISHED));
                    listTutorialPojo.add(tutorialPojo);
                }
                return listTutorialPojo;
            }

        // }

        return null;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private static JsonObject toJsonObj(String json) {
        JsonReader jReader = null;
        try {
            jReader = Json.createReader(new StringReader(json));
            return jReader.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private static JsonArray toJsonArray(String json) {
        JsonReader jReader = null;
        try {
            jReader = Json.createReader(new StringReader(json));
            return jReader.readArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
