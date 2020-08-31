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
		
		Response response= RestClient.doGet("JSON", baseURI, basePath, token, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test(priority = 2)
	public void getUserWithQueryParamsAPITest() {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "Eshana");
		
		Response response= RestClient.doGet("JSON", baseURI, basePath, token, params, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}

}
