package com.api.testing;

import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.*;
import java.util.HashMap;

public class apiChaining {
	
	Faker faker = new Faker();
	int extractedID;
	
	// create a function/test-case to CREATE USER
	
	@Test (priority = 1)
	public void createUserAPI() {
		
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", faker.name().firstName());
		requestBody.put("gender", faker.demographic().sex());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "active");
		
		extractedID=given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody)
			.post("https://gorest.co.in/public/v2/users").
			jsonPath().getInt("id");   // to extract id from the response body
			
			System.out.println("The id created is >> "+extractedID);
			
	//	.then()
	//		.statusCode(201)
	//		.log().status()
	//		.log().body();
	}
	
	@Test (priority = 2)
	public void listUsers() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.get("https://gorest.co.in/public/v2/users")
		
		.then()
			.log().body();			
}
	
	// create a function/test-case to UPDATE THE CREATED USER
	
	@Test (priority = 3)
	public void updateUserAPI() {
		
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", faker.name().prefix()+" "+faker.name().lastName());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "active");
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody)
			.patch("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.log().body();
			System.out.println("The id updated is >> "+extractedID);
	}
	
	// create a function/test-case to DELETE THE CREATED USER
	
	@Test (priority = 4)
	public void deleteUser() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")

		.when()
			.delete("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.statusCode(204)
			.log().status();
			System.out.println("The id deleted is >> "+extractedID);
	}
}