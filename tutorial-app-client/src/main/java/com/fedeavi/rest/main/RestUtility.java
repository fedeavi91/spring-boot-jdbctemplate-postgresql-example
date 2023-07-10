package com.fedeavi.rest.main;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.specimpl.ResponseBuilderImpl;

public class RestUtility {
    

    public static  Response getNewResp (Object r){
	    ResponseBuilderImpl res = new ResponseBuilderImpl();
	    res.status(Status.OK);
	    res.entity(r);
	    return res.build();
	  }
}
