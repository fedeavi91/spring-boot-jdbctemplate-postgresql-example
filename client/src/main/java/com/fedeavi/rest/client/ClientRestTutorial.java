package com.fedeavi.rest.client;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.ws.rs.core.Response;

import javax.ws.rs.core.Response.Status;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class ClientRestTutorial {

    private ResteasyClient client;

    private String url;

    public ClientRestTutorial() {
        this.url = "http://localhost:8081/api/tutorials";
    }

    public List<TutorialPojo> getAllTutorials() {

        List<TutorialPojo> listTutorialPojo = null;

        client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(this.url);
        Response r = target.request().get();

        String jsonString = r.readEntity(String.class);
        System.out.println("jsonString = " + jsonString);
        if (jsonString != null) {
            JsonObject responseJson = toJsonObj(jsonString);

            if (responseJson != null && responseJson.getString("responseStatus").equals(Status.OK.toString())) {
                listTutorialPojo = new ArrayList<TutorialPojo>();
                JsonArray jsonArray = responseJson.getJsonArray("");
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

        }

        return null;
    }

    public ClientRestTutorial(ResteasyClient client) {
        this.client = client;
    }

    public ResteasyClient getClient() {
        return client;
    }

    public void setClient(ResteasyClient client) {
        this.client = client;
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
}
