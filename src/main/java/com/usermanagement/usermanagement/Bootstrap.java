package com.usermanagement.usermanagement;


import com.mongodb.*;
 
import static spark.Spark.setIpAddress;
import static spark.Spark.setPort;
import static spark.SparkBase.staticFileLocation;
 
public class Bootstrap {
    private static final String IP_ADDRESS =  "localhost";
    private static final int PORT = 4567;
 
    public static void main(String[] args) throws Exception {
        setIpAddress(IP_ADDRESS);
        setPort(PORT);
        staticFileLocation("/Resources/public");
        new UserResource(new UserManagementService(mongo()));
    }
 
    private static DB mongo() throws Exception {

            MongoClient mongoClient = new MongoClient("localhost",27017);
            return mongoClient.getDB("usermanagement");
    }
}
