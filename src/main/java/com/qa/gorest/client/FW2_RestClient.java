package com.qa.gorest.client;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public  class FW2_RestClient {
	private static RequestSpecBuilder specBuilder;
	
	private static final String BASE_URI ="https://gorest.co.in/private/v2/users";
	private static final String AUTH ="173bfc971f0498b834246e2095d984b9816ddbf96e312f31b63d15560b02b336";
	static {
		specBuilder = new RequestSpecBuilder();
	}
	private void addAuthorizationHeader() {
		specBuilder.addHeader("Authorization", AUTH);
	}
	private void setRequestContentType(String contentType) {
		switch(contentType.toLowerCase()) {
		case "json" : specBuilder.setContentType(contentType); break;
		case "xml" : specBuilder.setContentType(contentType);  break;
		default :
			//throw new APIFrameworkException("Invalid ContentType");
			throw new RuntimeException("invalid contentType");
		}
	}
	private RequestSpecification createRequestSpec() {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		return specBuilder.build();
	}
	private RequestSpecification createRequestSpec(String contentType) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		if(contentType!=null) {
			setRequestContentType(contentType);
		}
		return specBuilder.build();
	}
	private RequestSpecification createRequestSpec(Map<String,String> headerMap, Map<String,String> queryParam) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		if(headerMap!=null) {
			specBuilder.addHeaders(headerMap);
		}
		if(queryParam!=null) {
			specBuilder.addQueryParams(queryParam);
		}
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(String contentType, Map<String,String> headerMap, Object reqBody) {
		specBuilder.setBaseUri(contentType);
		addAuthorizationHeader();
		if(headerMap!=null){
			specBuilder.addHeaders(headerMap);
		}
		if(reqBody!=null) {
			specBuilder.setBody(reqBody);
		}
		return specBuilder.build();
	}
	
	//GetCall
	public Response get(String serviceURL, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec()).log().all().when().get(serviceURL);
		}else
			return RestAssured.given(createRequestSpec()).when().get(serviceURL);
	}

}
