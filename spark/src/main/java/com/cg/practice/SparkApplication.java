package com.cg.practice;


import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.halt;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.delete;

import java.util.HashMap;







import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.Route;
import spark.TemplateEngine;

@SpringBootApplication
public class SparkApplication {

	static HashMap<Integer,HelloWorldPojo> hm=new HashMap<Integer,HelloWorldPojo>();
	
	
	public static void main(String[] args) {
		SpringApplication.run(SparkApplication.class, args);
		
		port(5678);
		
		

        get("/hello-world-service/hello/:id", (request, response) -> {
        	int id= Integer.parseInt(request.params(":id"));
        	if(hm.get(id)==null){
        		halt(500," data is not available ");
        	}
        	response.status(200);
        	response.type("application/json");
        	return hm.get(id);
        	
        },new Converter());
        

        get("/hello-world-service/hello", (request, response) -> {
        	
        	if(hm.isEmpty()){
        		halt(500," data is not available ");
        	}
        	response.status(200);
        	response.type("application/json");
        	return hm.values();
        	
        },new Converter());
        
       post("/hello-world-service/hello", (request, response) -> {
    	   String body= request.body();
    	   Gson gson=new GsonBuilder().create();

    	   
    	   HelloWorldPojo pojo=gson.fromJson(body,HelloWorldPojo.class);
    	   
    	
    	   hm.put(pojo.getId(), pojo);
    	   response.type("application/json");
    	   response.status(200);
    	   
    	  return pojo;
       },new Converter());
       
       
       put("/hello-world-service/hello/:id", (request, response) -> {
    	   String body= request.body();
    	   Gson gson=new GsonBuilder().create();
    	    int id= Integer.parseInt(request.params(":id"));
    	   HelloWorldPojo pojo= gson.fromJson(body,HelloWorldPojo.class);
    	
    	   
    	 
    	  if(id!=pojo.getId()){
    		  halt(500, "Id in url doesn't match with id in body");
    	  }else if(hm.get(id)==null){
    		  halt(500, "check your input");
    	  }else{
    		  hm.replace(pojo.getId(), pojo);
    		
    		  
    	  }
    	   
    	  response.type("application/json"); 
    	  response.status(200);
    	  return pojo;
       },new Converter());
       
       delete("/hello-world-service/hello/:id", (request, response) -> {
    	   String body= request.body();
    	   Gson gson=new GsonBuilder().create();
    	   int id= Integer.parseInt(request.params(":id"));
    	   HelloWorldPojo pojo= gson.fromJson(body,HelloWorldPojo.class);
    	
    	   
    	   
    	  if(id!=pojo.getId()){
    		  halt(500, "check your input");
    	  }else if(hm.get(id)==null){
    		  halt(500, "check your input");
    	  }else{
    		  
    		  hm.remove(pojo.getId());
    	  }
    	   
    	  response.type("application/json"); 
    	  response.status(200);
    	  return pojo;
       },new Converter());
       
       
       
       }

	
		
	}

	

