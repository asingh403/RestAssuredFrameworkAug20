package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class GetUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "4796aea4838ce5487518c706d49cc847b57e4196b52f0921539697e2d44bf060";
	
	
	@Test(priority = 1)
	public void getAllUserListAPITest() {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+ token);
		
		Response response= RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test(priority = 2)
	public void getUserWithQueryParamsAPITest() {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+ token);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "Eshana");
		
		Response response= RestClient.doGet("JSON", baseURI, basePath, authTokenMap, params, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}

}
