package com.api.testing;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;

public class getListUsersJsonSchemaValidation {
	
	@Test
	public void listUsers() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.get("https://gorest.co.in/public/v2/users")
		
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getAPIschema.json"))
			.log().status()
			.time(Matchers.greaterThan(100L))
			.log().body()		// print response body
			.statusCode(200)	// verify if statusCode=200
			.body("gender", hasItems("male","female"))
			.body("status", hasItems("active","inactive"))
			.header("Content-Type", "application/json; charset=utf-8");
	}
}