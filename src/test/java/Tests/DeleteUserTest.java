package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constants.Authentication;
import POJO.User;
import Resources.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest {
	
	@Test
    public void testDeleteWithPOJOLombokBuilder() {
    	User user = User.builder()
    		.name("Jayesh")
    		.status("active")
    		.email("jay1243@gmail.com")
    		.gender("male")
    		.build();
    	Response response = restClient.post("public/v2/users", user, null, null, Authentication.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 201);
        
        //grab user id
        String userId = response.jsonPath().getString("id");
        System.out.println("user id ==>"+ userId);
        
        //hit get call
        Response responseGet = restClient.get("public/v2/users/"+userId, null, null, Authentication.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(responseGet.getStatusCode(), 200);
        Assert.assertEquals(responseGet.jsonPath().getString("id"), userId);
        
                
        //DELETE user
        Response responseDelete = restClient.delete("public/v2/users/"+userId, null, null, Authentication.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(responseDelete.getStatusCode(), 204);
        
        //GET call
        Response responseGet1 = restClient.get("public/v2/users/"+userId, null, null, Authentication.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(responseGet1.getStatusCode(), 404);
        Assert.assertEquals(responseGet1.jsonPath().getString("message"), "Resource not found");

    }

}
