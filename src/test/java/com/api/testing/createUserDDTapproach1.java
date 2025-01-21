package com.api.testing;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.json.JSONObject;

public class createUserDDTapproach1 {
	
	@DataProvider (name="ddt")
	public Object DDT() {
		Object [][] requestBody = new Object[4][4];
	
		requestBody[0][0]="Kiran";
		requestBody[0][1]="male";
		requestBody[0][2]="kirana@apicourse.com";
		requestBody[0][3]="active";
		
		requestBody[1][0]="Eashwar";
		requestBody[1][1]="male";
		requestBody[1][2]="eashwaar@postman.com";
		requestBody[1][3]="inactive";
		
		requestBody[2][0]="Nourin";
		requestBody[2][1]="female";
		requestBody[2][2]="nourian@selenium.com";
		requestBody[2][3]="active";
		
		requestBody[3][0]="Ishanth";
		requestBody[3][1]="male";
		requestBody[3][2]="ishanath@testingcourse.com";
		requestBody[3][3]="inactive";
		
		return requestBody;
	}
	
	@Test (dataProvider = "ddt")
	public void createUserAPI(String name, String gender, String email, String status) {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", name);
		requestBody.put("gender", gender);
		requestBody.put("email", email);
		requestBody.put("status", status);
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody.toString())
			.post("https://gorest.co.in/public/v2/users")
		
		.then()
			.statusCode(201)
			.log().status()
			.log().body()
			.assertThat().body("gender", oneOf("male", "female"))
			.assertThat().body("status", oneOf("active", "inactive"))
			.time(Matchers.lessThan(3000L))
			.header("Content-Type", "application/json; charset=utf-8");		
	}
}