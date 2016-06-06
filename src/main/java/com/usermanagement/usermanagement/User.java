package com.usermanagement.usermanagement;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
 
import java.util.Date;

 
public class User {

	class Address
	{
		public String street;
		public String city;
		public String zip;
		public String state;
		public String country;
	}
	
	class Company
	{
		public String name;
		public String website;
	}

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address= new Address();
    private Date dateCreated= new Date();
    private Company company=new Company();
    private String profilepic;
    
    public User()
    {
    	
    }
 
    public User(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
    }
 
    public String getId(){
    	return this.id;
    }
    public String getFirstName(){
    	return firstname;
    }
    
    public String getLastName(){
    	return lastname;
    }
    
    public String getEmail(){
    	return email;
    }
    
    public String getStreet(){
    	return address.street;
    }
    
    public String getCity(){
    	return address.city;
    }
    
    public String getCountry(){
    	return address.country;
    }
    
    public String getState(){
    	return address.state;
    }
    
    public String getZip(){
    	return address.zip;
    }
    
    public Date getDateCreated() {
        return dateCreated;
    }
    
    public String getCompanyName(){	
    	return company.name;					
    }
    
    public String getCompanySite(){
    	return company.website;
    }
    
    public String getProfilePic(){
    	return profilepic;
    }
    
    public void setId(String id){
    	this.id = id;
    }
    /*public void setFirstName(String x){
    	firstname = x;
    }
    
    public void setLastName(String x){
    	lastname=x;
    }
    
    public void setEmail(String x){
    	 email=x;
    }
    
    public void setStreet(String x){
    	address.street=x;
    }
    
    public void setCity(String x){
    	address.city=x;
    }
    
    public void setCountry(String x){
    	address.country=x;
    }
    
    public void setState(String x){
    	address.state=x;
    }
    
    public void setZip(String x){
    	address.zip=x;
    }
    
    public void setDateCreated(Date date) {
         dateCreated= date;
    }
    
    public void setCompanyName(String x){	
    	company.name=x;					
    }
    
    public void getCompanySite(String x){
    	company.website =x;
    }
    
    public void setProfilePic(String x){
    	profilepic=x;
    }*/
    
}