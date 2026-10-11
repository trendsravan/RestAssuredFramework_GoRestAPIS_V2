package com.qa.gorest.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.frameworkExcemptions.APIFrameworkException;

import io.restassured.response.Response;

public class JsonPathQueryUtils {
	
	public <T> T read(Response response, String jsonPath) {
		try {
			return JsonPath.read(response.asString(), jsonPath);
		}catch(PathNotFoundException e) {
			throw new APIFrameworkException("Incorrect jsonPath", e);
		}
	}
	
	public <T> List<T>  readList(Response response, String jsonPath) {
		try {
			return JsonPath.read(response.asString(), jsonPath);
		}catch(PathNotFoundException e){
			throw new APIFrameworkException("Incorrect jsonPath", e);
		}
	}
	
	public <T> List<Map<String,T>> readListOfMap(Response response, String jsonPath) {
		try {
			return JsonPath.read(response.asString(), jsonPath);
		}catch(PathNotFoundException e) {
			throw new APIFrameworkException("Incorrect jsonPath", e);
		}
	}
	
}
