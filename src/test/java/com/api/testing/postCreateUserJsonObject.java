package com.api.testing;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.json.JSONObject;

public class postCreateUserJsonObject {
	
	@Test
	public void createUserAPI() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "json");
		requestBody.put("gender", "female");
		requestBody.put("email", "json1@object.com");
		requestBody.put("status", "active");
		
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
			.body("name", equalTo("json"))
			.time(Matchers.lessThan(2000L))
			.header("Content-Type", "application/json; charset=utf-8");		
	}
}