package com.qa.gorest.restclient;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.Map;

import com.qa.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * 
 * @author ASHUTOSH SINGH
 *
 */

public class RestClient {

	/**
	 * This method is used to call GET API
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @return This method returning the reponse from the GET call	
	 */
	
	public static Response doGet(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> paramsMap, boolean log) {

		
		if(setBaseURI(baseURI)) {
			setBaseURI(baseURI);
			RequestSpecification request = createRequest(contentType, token, paramsMap, log);
			
			return getResponse("GET", request, basePath);
			
		}
		
		return null;
	}
		
	
	/**
	 * This method is used to call POST API
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @param obj
	 * @return This method is returning response from POST call.
	 */
		
		public static Response doPost(String contentType, String baseURI, String basePath, Map<String, String> token,
				Map<String, String> paramsMap, boolean log, Object obj) {

			
			if(setBaseURI(baseURI)) {
				
				RequestSpecification request = createRequest(contentType, token, paramsMap, log);
				addRequestPayLoad(request, obj);
				return getResponse("POST", request, basePath);
			}
			return null;
	}
		
		public static void addRequestPayLoad(RequestSpecification request, Object obj) {
			if(obj instanceof Map) {
				request.formParams((Map<String, String>)obj);
			}else {
			
				String jsonPayload = TestUtil.getSerializedJSON(obj);
			    request.body(jsonPayload);
			}
		}
		

	private static boolean setBaseURI(String baseURI) {
		if(baseURI == null || baseURI.isEmpty()) {
			System.out.println("Please pass the correct baseURI. . . either it is null or blank/empty. . .");
			return false;
		}
		
		try {
		RestAssured.baseURI = baseURI;
		return true;
		
		}catch(Exception e) {
			System.out.println("Some exception got Occured while assiging the base URL with RestAssured");
			return false;
		}
	}

	private static RequestSpecification createRequest(String contentType, Map<String, String> token, Map<String, String> paramsMap,boolean log) {
		
		
		RequestSpecification request;
		
		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}

		if (token.size() > 0) {
			//request.header("Authorization", "Bearer " + token);
			request.headers(token);
			
		}

		if (!(paramsMap == null)) {
			request.queryParams(paramsMap);
		}

		
		
		if(contentType !=null) {
		
		if (contentType.equalsIgnoreCase("JSON")) {
			
						request.contentType(ContentType.JSON);
			
		} else if 
			(contentType.equalsIgnoreCase("XML")) {
						request.contentType(ContentType.XML);
		
		} else if 
			(contentType.equalsIgnoreCase("TEXT")) {
						request.contentType(ContentType.TEXT);
		}else if 
			(contentType.equalsIgnoreCase("multipart")) {
			request.multiPart("image", new File("C:\\Users\\ASHUTOSH SINGH\\Desktop\\TestNg-Issue\\imgur_1.jpeg"));
		}
	}
		
		return request;

	}
	
	
	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
		return executeAPI(httpMethod, request, basePath);
	}
	
	private static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
		
		Response response = null;
		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;
			
		case "POST":
			response = request.post(basePath);
				break;

		case "PUT":
			response = request.put(basePath);
			break;
				
		case "DELETE":
			response = request.delete(basePath);
			break;
		default:
			
			System.out.println("PLEASE PASS CORRECT HTTP METHOD. . . .");
			break;
		}
		
		return response;
		
	}
}
