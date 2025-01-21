package com.api.testing;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.json.JSONObject;

public class createUserDDTFakerLibraryApproach2 {
	
	Faker faker = new Faker();
	
	@DataProvider (name="ddt")
	public Object[][] DDT() {
	
		return new Object[][] {
		
			{faker.name().firstName(),faker.demographic().sex(),faker.internet().emailAddress(),"active"},
			{faker.name().firstName(),faker.demographic().sex(),faker.internet().emailAddress(),"active"},
			{faker.name().firstName(),faker.demographic().sex(),faker.internet().emailAddress(),"inactive"},
			{faker.name().firstName(),faker.demographic().sex(),faker.internet().emailAddress(),"inactive"}
		};
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