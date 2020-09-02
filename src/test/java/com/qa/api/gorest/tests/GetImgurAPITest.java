package com.qa.api.gorest.tests;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.util.Token;

import io.restassured.response.Response;

public class GetImgurAPITest {
	
	Map<Object, Object> tokenMap;
	String accessToken;
	String accountUserName;
	String refreshToken;
	
	@BeforeMethod
	public void setUp() {
		tokenMap = Token.getAccessToken();
		accessToken= tokenMap.get("access_token").toString();
		accountUserName= tokenMap.get("account_username").toString();
		refreshToken= tokenMap.get("refresh_token").toString();
	}
	
	
	
	@Test
	public void getAccountBlockStatusTest() {
		
		String baseURI = "https://api.imgur.com/account";
		String basePath =  "/v1/"+accountUserName+"/block";
		
		Response response = RestClient.doGet(null, baseURI, basePath, accessToken, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	@Test
	public void getAccountImagesTest() {
		
		String baseURI = "https://api.imgur.com";
		String basePath =  "/3/account/me/images";
		
		Response response = RestClient.doGet(null, baseURI, basePath, accessToken, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	
	
	
	
	
	
	
	

}
