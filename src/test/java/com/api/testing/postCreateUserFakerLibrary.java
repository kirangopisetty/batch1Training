package com.api.testing;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.json.JSONObject;

public class postCreateUserFakerLibrary {
	
	Faker faker = new Faker();
	
	@Test
	public void createUserAPI() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", faker.name().firstName());
		requestBody.put("gender", faker.demographic().sex());
		requestBody.put("email", faker.internet().emailAddress());
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
			.time(Matchers.lessThan(3000L))
			.header("Content-Type", "application/json; charset=utf-8");		
	}
}