package com.qa.gorest.client;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.frameworkExcemptions.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
//	private static final String BASE_URI="https://gorest.co.in";
//	private static final String AUTH = "173bfc971f0498b834246e2095d984b9816ddbf96e312f31b63d15560b02b336";

	private RequestSpecBuilder specBuilder;
	private Properties prop;
	private String baseURI;
	private boolean isAuthHeaderAdded;

	// Properties is used to get the data from the Config.Properties
	public RestClient(Properties prop, String baseURI) {
		specBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
	}

	private void addAuthorizationHeader() {
		if(!isAuthHeaderAdded) {  //["if header NOT added yet"][ !false → true → IF executes]
			specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
			isAuthHeaderAdded = true;
		}
	}

	private void setRequestContentType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "html":
			specBuilder.setContentType(ContentType.HTML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "form-data":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;
		case "x-www-form-urlencoded":
			specBuilder.setContentType(ContentType.URLENC);
			break;
		default:
			System.out.println("Pas a valid conntentType");
			throw new APIFrameworkException("InvalidcontentType");
		}
	}

	// FOR GET-CALL
	private RequestSpecification createRequestSpec(boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if (includeAuth) {
			addAuthorizationHeader();
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if (includeAuth) {
			addAuthorizationHeader();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headerMap, Map<String, String> addQuery,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if (includeAuth) {
			addAuthorizationHeader();
		}
		if (headerMap != null) {
			specBuilder.addHeaders(headerMap);
		}
		if (addQuery != null) {
			specBuilder.addQueryParams(addQuery);
		}
		return specBuilder.build();
	}

	// FOR POST-CALL
	private RequestSpecification createRequestSpec(Object requestBody, String contentType, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		setRequestContentType(contentType);
		if (includeAuth) {
			addAuthorizationHeader();
		}
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		setRequestContentType(contentType);
		if (includeAuth) {
			addAuthorizationHeader();
		}
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}

	// -----------------------------------HTTP METHOD UTILITIES---------------
	// 1) GET CALL
	public Response get(String serviceURL, boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().log().all().get(serviceURL);
		} else
			return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceURL);
	}

	public Response get(String serviceURL, Map<String, String> headerMap, boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(headerMap, includeAuth)).log().all().when().get(serviceURL);
		} else
			return RestAssured.given(createRequestSpec(headerMap, includeAuth)).when().get(serviceURL);
	}

	public Response get(String serviceURL, Map<String, String> HeaderMap, Map<String, String> QueryParam,
			boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(HeaderMap, QueryParam, includeAuth)).log().all().when()
					.get(serviceURL);
		} else
			return RestAssured.given(createRequestSpec(HeaderMap, QueryParam, includeAuth)).when().get(serviceURL);
	}

	// ---POST CALL
	public Response post(String serviceURl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
					.when().log().all().post(serviceURl);
		} else
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).post(serviceURl);
	}

	public Response post(String serviceURl, String contentType, Object requestBody, Map<String, String> HeaderMap,
			boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap, includeAuth)).log().all()
					.post(serviceURl);
		} else
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap, includeAuth))
					.post(serviceURl);
	}

	// ---PUT CALL
	public Response put(String serviceURl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
					.put(serviceURl);
		} else
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).put(serviceURl);
	}

	public Response put(String serviceURl, String contentType, Object requestBody, Map<String, String> HeaderMap,
			boolean includAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap, includAuth)).log().all()
					.put(serviceURl);
		} else
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap, includAuth))
					.put(serviceURl);
	}

	// ---PATCH CALL
	public Response patch(String serviceURl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
					.patch(serviceURl);
		} else
			return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).patch(serviceURl);
	}

	public Response patch(String serviceURl, String contentType, Object requestBody, Map<String, String> HeaderMap,
			boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap, includeAuth)).log().all()
					.patch(serviceURl);
		} else
			return RestAssured.given(createRequestSpec(requestBody, contentType, HeaderMap, includeAuth))
					.patch(serviceURl);
	}

	// ---DELETE CALL

	public Response delete(String serviceURL, boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().delete(serviceURL);
		} else
			return RestAssured.given(createRequestSpec(includeAuth)).delete(serviceURL);

	}
}
