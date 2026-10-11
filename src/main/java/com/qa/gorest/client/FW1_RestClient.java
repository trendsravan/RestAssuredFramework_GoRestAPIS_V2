package com.qa.gorest.client;

import java.util.Map;

import com.qa.gorest.frameworkExcemptions.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FW1_RestClient {
private static RequestSpecBuilder specBuilder;
	
	static {
		specBuilder = new RequestSpecBuilder();
	}
	private static final String BASE_URI="https://gorest.co.in";
	private static final String AUTH = "173bfc971f0498b834246e2095d984b9816ddbf96e312f31b63d15560b02b336";
	
	private void addAuthorizationHeader() {
		 specBuilder.addHeader("Authorization", "Bearer "+AUTH);
	}
	
	private void setRequestContentType(String contentType) {
		switch(contentType.toLowerCase()){
		case "json"		:specBuilder.setContentType(ContentType.JSON);break;
		case "xml" 		:specBuilder.setContentType(ContentType.XML);break;
		case "html"		:specBuilder.setContentType(ContentType.HTML);break;
		case "text"		:specBuilder.setContentType(ContentType.TEXT);break;
		case "multipart" : specBuilder.setContentType(ContentType.MULTIPART);break;
		case "urlencoded": specBuilder.setContentType(ContentType.URLENC);break;
		default :
			System.out.println("Pas a valid conntentType");
			throw new APIFrameworkException("InvalidcontentType");
		}
	}
	
	//FOR GET-CALL
	private RequestSpecification createRequestSpec() {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		return specBuilder.build();
	}
	private RequestSpecification createRequestSpec(Map<String,String> headersMap) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		if(headersMap!=null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}
	private RequestSpecification createRequestSpec(Map<String,String> headerMap, Map<String,String> addQuery) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		if(headerMap!=null) {
			specBuilder.addHeaders(headerMap);
		}
		if(addQuery!=null) {
			specBuilder.addQueryParams(addQuery);
		}
		return specBuilder.build();
	}
	
	//FOR POST-CALL
	private RequestSpecification createRequestSpec(Object requestBody, String contentType) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		if(requestBody!= null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	private RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String,String> headersMap) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		if(requestBody!= null) {
			specBuilder.setBody(requestBody);
		}
		if(headersMap!= null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}
	
	//-----------------------------------HTTP -> METHOD UTILS-----------------------------------------------------
	//---GET CALL
	public Response get(String serviceURL, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec()).log().all().when().get(serviceURL);
		}else
			return RestAssured.given(createRequestSpec()).when().get(serviceURL);
	}
	
	public Response get(String serviceURL, Map<String,String> headerMap, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(headerMap)).log().all().when().get(serviceURL);
		}else
			return RestAssured.given(createRequestSpec(headerMap)).when().get(serviceURL);
	}
	
	public Response get(String serviceURL, Map<String,String> QueryParam,Map<String,String> HeaderMap, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(HeaderMap, QueryParam)).log().all().when().get(serviceURL);
		}else
		return RestAssured.given(createRequestSpec(HeaderMap, QueryParam)).when().get(serviceURL);
	}
	//---POST CALL
	public Response post(String serviceURl, String contentType ,Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().post(serviceURl);
		}else
			return RestAssured.given(createRequestSpec(requestBody, contentType)).post(serviceURl);
	}
	public Response post(String serviceURl, String contentType ,Object requestBody, Map<String,String> HeaderMap, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap)).log().all().post(serviceURl);
		}else
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap)).post(serviceURl);
	}
	
	//---PUT CALL
	public Response put(String serviceURl, String contentType ,Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().put(serviceURl);
		}else
			return RestAssured.given(createRequestSpec(requestBody, contentType)).put(serviceURl);
	}
	public Response put(String serviceURl, String contentType ,Object requestBody, Map<String,String> HeaderMap, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap)).log().all().put(serviceURl);
		}else
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap)).put(serviceURl);
	}
	
	//---PATCH CALL
	public Response patch(String serviceURl, String contentType ,Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().patch(serviceURl);
		}else
			return RestAssured.given(createRequestSpec(requestBody, contentType)).patch(serviceURl);
	}
	public Response patch(String serviceURl, String contentType ,Object requestBody, Map<String,String> HeaderMap, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap)).log().all().patch(serviceURl);
		}else
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap)).patch(serviceURl);
	}
	
	//---DELETE CALL
	
	public Response delete(String serviceURL, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec()).log().all().delete(serviceURL);
		}else
			return RestAssured.given(createRequestSpec()).delete(serviceURL);
		
	}
}
