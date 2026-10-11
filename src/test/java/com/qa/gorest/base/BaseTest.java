package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	//Service URL's
	public static final String GOREST_SURL = "/public/v2/users";
	public static final String FAKESTORE_SURL = "/products";
	
	protected ConfigurationManager config;
	protected RestClient restClient;
	protected Properties prop;
	protected String baseURI;
	
	@Parameters({"baseURI"})
	@BeforeTest 
	public void setUp(String baseURI) {
		
		RestAssured.filters(new AllureRestAssured());
		config = new ConfigurationManager();
		prop = config.initProp();
		//String baseURI = prop.getProperty("baseURI");
		this.baseURI = baseURI;
		//restClient = new RestClient(prop, baseURI);
	}
}
