package Tests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Client.RestClient;
import Constants.Authentication;
import POJO.User;
import Resources.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiTests extends BaseTest {
	
	 @Test
	    public void testGetWithJsonContentType() {
	        Map<String, String> queryParams = new HashMap<>();
	        queryParams.put("name", "naveen");

	        Response response = restClient.get("/public/v2/users", queryParams, null, Authentication.BEARER_TOKEN, ContentType.JSON);
	        Assert.assertEquals(response.getStatusCode(), 200);
	    }
	    
	    @Test
	    public void testGetWithJsonContentTypeWithAuthHeader() {
	        Response response = restClient.get("/public/v2/users", null, null, Authentication.BEARER_TOKEN, ContentType.JSON);
	        Assert.assertEquals(response.getStatusCode(), 200);
	    }
	    
	    @Test
	    public void testPostWithJsonFile() {
	        // Path to the JSON file that contains the request body
	        File jsonFile = new File(".\\src\\test\\java\\Resources\\User.json");
	        Response response = restClient.postWithFile("/public/v2/users", jsonFile, null, null, Authentication.BEARER_TOKEN, ContentType.JSON);
	        Assert.assertEquals(response.getStatusCode(), 201);
	    }
	    
	    
	 // Utility method to generate a random email
	    public String generateRandomEmail() {
	        String randomString = UUID.randomUUID().toString().substring(0, 5);  // generates a 5-character random string
	        return randomString + "@example.com";
	    }

	    // DataProvider method supplying different User objects with random email IDs
	    @DataProvider(name = "userDataProvider")
	    public Object[][] userDataProvider() {
	        return new Object[][] {
	            { new User(null, "Rushi1222", generateRandomEmail(), "male", "active") },
	            { new User(null, "Niki2222", generateRandomEmail(), "female", "active") },
	            { new User(null, "yash3212", generateRandomEmail(), "male", "inactive") }
	        };
	    }

       // Test method that takes User data from the DataProvider
	    @Test(dataProvider = "userDataProvider")
	    public void testPostWithPOJOLombok(User user) {
	        RestClient restClient = new RestClient();
	        Response response = restClient.post("public/v2/users", user, null, null, Authentication.BEARER_TOKEN, ContentType.JSON);
	        response.prettyPrint();
	        Assert.assertEquals(response.getStatusCode(), 201);
	    }
	    
	    @Test
	    public void testPostWithPOJOLombok() {
	    	User user = new User(null,"Rushikesh123", "Naveenapifw232@gmail.com", "male", "active");
	    	Response response = restClient.post("public/v2/users", user, null, null, Authentication.BEARER_TOKEN, ContentType.JSON);
	        Assert.assertEquals(response.getStatusCode(), 201);
	    }
	    
	    @Test
	    public void testPostWithPOJOLombokBuilder() {
	    	User user = User.builder()
	    		.name("Namanshree")
	    		.status("active")
	    		.email("rushi4321@gmail.com")
	    		.gender("male")
	    		.build();
	    	Response response = restClient.post("public/v2/users", user, null, null, Authentication.BEARER_TOKEN, ContentType.JSON);
	        Assert.assertEquals(response.getStatusCode(), 201);
	    }
	    

	    
	}



	


