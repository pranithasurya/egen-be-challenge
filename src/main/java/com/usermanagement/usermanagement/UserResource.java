package com.usermanagement.usermanagement;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
 
import java.util.HashMap;
 
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class UserResource {

    private static final String API_CONTEXT = "/api/v1";
    
    private final UserManagementService userService;
 
    public UserResource(UserManagementService userService) {
        this.userService = userService;
        setupEndpoints();
    }
 
    private void setupEndpoints() {
        post(API_CONTEXT + "/users", "application/json", (request, response) -> {
        	userService.createNewUser(request.body());
            response.status(201);
            return response;
        });
 
        get(API_CONTEXT + "/users/:id", "application/json", (request, response)-> userService.find(request.params(":id")), new JsonTransformer());
 
        get(API_CONTEXT + "/users", "application/json", (request, response)
 
                -> userService.getAllUsers(), new JsonTransformer());
 
        put(API_CONTEXT + "/users/:id", "application/json", (request, response)
 
                -> userService.updateUser(request.params(":id"), request.body()));
    }
 
}

