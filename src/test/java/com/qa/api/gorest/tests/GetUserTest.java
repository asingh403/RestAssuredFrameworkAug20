package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.gorest.restclient.RestClient;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;


@Epic("Get User Imgur user BLOCK status api feature implementation.....")
@Feature("Get User Imgur api feature.....")
public class GetUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "4796aea4838ce5487518c706d49cc847b57e4196b52f0921539697e2d44bf060";
	
	
	@Test(priority = 1)
	@Description("Get the User current status")
	@Severity(SeverityLevel.NORMAL)
	public void getAllUserListAPITest() {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+ token);
		
		Response response= RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test(priority = 2)
	@Description("Get the User existing on every pagination...Very the all user list on per page on GET Call")
	@Severity(SeverityLevel.CRITICAL)
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
