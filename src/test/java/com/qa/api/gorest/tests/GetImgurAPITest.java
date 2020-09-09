package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.util.Token;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("Get User Imgur user BLOCK status api feature implementation.....")
@Feature("Get User Imgur api feature.....")
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
	
	
	
	@Test(priority = 1)
	@Description("Get the User current status")
	@Severity(SeverityLevel.BLOCKER)
	public void getAccountBlockStatusTest() {
		
		String baseURI = "https://api.imgur.com/account";
		String basePath =  "/v1/"+accountUserName+"/block";
		
		Map<String, String> authTokenMap=Token.getAuthToken();
		
		Response response = RestClient.doGet(null, baseURI, basePath, authTokenMap, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	@Test(priority = 2)
	@Description("Get the list of the user having the number of Imgaes having")
	@Severity(SeverityLevel.NORMAL)
	public void getAccountImagesTest() {
		
		String baseURI = "https://api.imgur.com";
		String basePath =  "/3/account/me/images";
		Map<String, String> authTokenMap=Token.getAuthToken();
		Response response = RestClient.doGet(null, baseURI, basePath, authTokenMap, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	@Test
	public void uploadImagePostAPITest() {
		Map<String, String> clientMap = Token.getClientId();
		
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title", "test title API");
		formMap.put("description", "test description API");
		
		Response response = RestClient.doPost("multipart", "https://api.imgur.com", "/3/image", clientMap, null, true, formMap);
		
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	
	
	
	
	
	
	
	

}
