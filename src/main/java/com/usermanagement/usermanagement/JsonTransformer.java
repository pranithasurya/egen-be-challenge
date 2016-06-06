package com.usermanagement.usermanagement;

import com.google.gson.Gson;
import spark.ResponseTransformer;
 
public class JsonTransformer implements ResponseTransformer {
 
    private Gson gson = new Gson();
 
    public JsonTransformer(){
    	
    }
    
    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
 
}