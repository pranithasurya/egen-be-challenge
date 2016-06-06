package com.usermanagement.usermanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import org.bson.types.ObjectId;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserManagementService {

    private final DB db;
    private final DBCollection collection;
    GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
    /* Register custom serializers with GsonBuilder */
    Gson gson;
    
    public UserManagementService(DB db) {
        this.db = db;
        this.collection = db.getCollection("users");
        gsonBuilder.registerTypeAdapter(Date.class, new CustomDateSerializer());
        gsonBuilder.registerTypeAdapter(Date.class, new CustomDateDeserializer());
        gson = gsonBuilder.create(); 
    }
    
    public void createNewUser(String body)
    {
    	
    	User user = gson.fromJson(body, User.class);
    	System.out.println(user.getFirstName());
    	
    	collection.insert(new BasicDBObject("firstname", user.getFirstName()).append("lastname", user.getLastName())
    					.append("email", user.getEmail())
    					.append("address", new BasicDBObject("street",user.getStreet())
    							.append("city", user.getCity())
    							.append("zip", user.getZip())
    							.append("state", user.getState())
    							.append("country", user.getCountry()))
    					.append("dateCreated", user.getDateCreated())
    					.append("company", new BasicDBObject("name",user.getCompanyName()).append("website", user.getCompanySite()))
    					.append("profilepic", user.getProfilePic())
    			);
    }
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        DBCursor dbObjects = collection.find();
        
        
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            User u = gson.fromJson(dbObject.toString(),User.class);
            u.setId(dbObject.get("_id").toString());
            
            users.add(u);
        }
        return users;
    }
    
    public User find(String id) {
        return new User((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }
    
    public User updateUser(String userId, String body) {
        User user = new Gson().fromJson(body, User.class);
        collection.update(new BasicDBObject("_id", new ObjectId(userId)), new BasicDBObject("$set", new BasicDBObject("firstname", user.getFirstName()).append("lastname", user.getLastName())
				.append("email", user.getEmail())
				.append("address", new BasicDBObject("street",user.getStreet()).append("city", user.getCity()).append("zip", user.getZip())).append("state", user.getState()).append("country", user.getCountry())
				.append("company", new BasicDBObject("name",user.getCompanyName()).append("website", user.getCompanySite()))
				.append("profilepic", user.getProfilePic())));
        return this.find(userId);
    }
 
    
}


