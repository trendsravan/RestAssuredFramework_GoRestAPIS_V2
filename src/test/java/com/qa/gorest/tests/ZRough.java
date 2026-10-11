package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.lombokpojo.User;
import com.qa.gorest.utils.StringUtils;

public class ZRough extends BaseTest {
	
	@BeforeMethod
	public void createRestClientObject() {
		restClient = new RestClient(prop, baseURI);
	}

	@Test
	public void createGetUser() {

		User user = new User("Sravan", StringUtils.getRandomMail(), "Male", "Active");

		int id = restClient.post("/public/v2/users", "json", user, true, true).then().log().all().statusCode(201).
				extract().path("id");
		System.out.println("UserID ==>" + id);
		
		RestClient restclient = new RestClient(prop, baseURI);
		restclient.get("/public/v2/users/"+id, true, true).then().log().all();
		

	}

}
