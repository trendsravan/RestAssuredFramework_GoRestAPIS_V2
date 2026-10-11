package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIConstants;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.lombokpojo.User;
import com.qa.gorest.utils.ExcelUtil;
import com.qa.gorest.utils.StringUtils;

public class T2_CreateUserTest extends BaseTest{
	
	@BeforeMethod
	public void createRestClientObject() {
		restClient = new RestClient(prop,baseURI);
	}
	
	@DataProvider
	public Object[][] userData() {
		return new Object[][]{
			{"Manager","Female","active"},
			{"Lead","Male","Inactive"}
			};
	}
	
	@DataProvider
	public Object[][] userExcelData() {
		return ExcelUtil.readExcel(APIConstants.EXCEL_SHEET_NAME);
	}
	
	@Test(dataProvider = "userExcelData")
	public void creteAndGetUser(String name, String gender, String status) {
		User user = new User(name ,StringUtils.getRandomMail(), gender, status);
		Integer id = restClient.post(GOREST_SURL, "json", user, true,true).then().log().all()
				.assertThat().statusCode(APIHttpStatus.CREATED.getCode())
				.extract().path("id");
		System.out.println("userid==== " + id);
		
//		RestClient rest2 = new RestClient();
//		System.out.println("SpecBuilder2 hashcode: " +rest2.hashCode());
		restClient.get(GOREST_SURL+"/"+id, true , true).then().log().all();
	}

}
