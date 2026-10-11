package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;

public class T3_FakeStoreTest extends BaseTest {

	@Test
	public void GetFakeStoreAPI() {
		restClient.get("/products", false, true).then().log().all().statusCode(APIHttpStatus.OK.getCode());
	}

}
