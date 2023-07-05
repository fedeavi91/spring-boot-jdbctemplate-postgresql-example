package com.fedeavi.rest.main;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fedeavi.rest.TutorialRestService;



@ApplicationPath("/rest")
public class RestEasyServices extends Application{

  private Set<Object> singletons= new HashSet<>();
  private Set<Class<?>> perRequest = new HashSet<>();
  
  public RestEasyServices() {
    buildSingletons();
    buildPerRequest();
  }

  private void buildPerRequest() {
    perRequest.add(TutorialRestService.class);


  }

  private void buildSingletons() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public Set<Object> getSingletons() {
      return singletons;
  }
  
  @Override
  public Set<Class<?>> getClasses() {
      return perRequest;
  }
}

