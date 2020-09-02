package com.qa.gorest.util;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;


public class Token {
	
	
	public static Map<Object, Object> getAccessToken() {

		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("refresh_token", "39a17fdfc1fd914d2a9651aa74c8a4013044ab01");
		formParams.put("client_id", "66f4918ab1b4d24");
		formParams.put("client_secret", "3d45304d1fba70f9f67bc28993ed6e6c36f2f32f");
		formParams.put("grant_type", "refresh_token");
		
		JsonPath tokenJson= 
		given()
			.formParams(formParams)
				.when()
					.post("https://api.imgur.com/oauth2/token")
					.then()
						.extract().jsonPath();
		
		System.out.println(tokenJson.getMap(""));
		
		return tokenJson.getMap("");
			
	}
	


}
