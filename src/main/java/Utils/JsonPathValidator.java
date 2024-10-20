package Utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;


import io.restassured.response.Response;

public class JsonPathValidator {
	
	
	public static String getResponseAsString(Response response) {
		return response.asString();
	}
	
	
	public static <T> T read(Response response, String jsonPath) {
		String jsonResponse =  getResponseAsString(response);
        try {
        	return JsonPath.read(jsonResponse, jsonPath);
        }
        catch(PathNotFoundException e) {
        	e.printStackTrace();
        	throw new PathNotFoundException(e);
        }
	}
	
	public static <T> List<T> readlist(Response response, String jsonPath) {
		String jsonResponse =  getResponseAsString(response);
        try {
        	return JsonPath.read(jsonResponse, jsonPath);
        }
        catch(PathNotFoundException e) {
        	e.printStackTrace();
        	throw new PathNotFoundException(e);
        }
	}
	
	public static <T> List<Map<T,T>> readListOfMaps(Response response, String jsonPath) {
		String jsonResponse =  getResponseAsString(response);
        try {
        	return JsonPath.read(jsonResponse, jsonPath);
        }
        catch(PathNotFoundException e) {
        	e.printStackTrace();
        	throw new PathNotFoundException(e);
        }
	}
	
	

}
