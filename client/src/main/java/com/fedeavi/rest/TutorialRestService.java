package com.fedeavi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/TutorialRestService")
public class TutorialRestService {

    @GET
    @Path("/getAllTutorials")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTutorials() {
        // Gson gson = new GsonBuilder().setDateFormat("dd/MM/YYYY").create();
        // log.info("RicercaVerbalePagamentoRequest: " + gson.toJson(ricVerbPagReq, RicercaVerbalePagamentoRequest.class));
        Response response = new TutorialRestController().getAllTutorials();

        return response;
    }

}
