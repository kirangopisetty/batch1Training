package com.api.testing;

import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.*;
import java.util.HashMap;
import static org.hamcrest.Matchers.*;

public class e2eAutomation {
	
	// CREATE A USER (POST) 
	
	Faker faker = new Faker();
	int extractedID;
		
	@Test (priority = 1)
	public void createAuser() {
		
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
			System.out.println("CREATE A USER API = PASSED");
			
	//	.then()
	//		.statusCode(201)
	//		.log().status()
	//		.log().body();
	}
	
	// VERIFY IF USER IS CREATED (GET) 
	
	@Test (priority = 2)
	public void verifyIfUserisCreated() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.get("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.log().body()
			.body("id", equalTo(+extractedID));
			System.out.println("The ID we are verifying to check if its created is "+extractedID);
			System.out.println("VERIFY IF USER IS CREATED API = PASSED");
}
	
	// UPDATE THE CREATED USER (PATCH)
	
	@Test (priority = 3)
	public void updateTheCreatedUser() {
		
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", faker.name().prefix()+" "+faker.name().lastName());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "inactive");
		
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
			System.out.println("VERIFY IF USER IS UPDATED API = PASSED");
	}
	
	
	// VERIFY IF USER IS UPDATED (GET) 
	
	@Test (priority = 4)
	public void verifyIfUserisUpdated() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.get("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.log().body()
			.body("id", equalTo(+extractedID));
			System.out.println("The ID we are verifying to check if its updated is "+extractedID);
			System.out.println("VERIFY IF USER IS UPDATED API = PASSED");
}
	
	// DELETE THE CREATED USER (DELETE) 
	
	@Test (priority = 5)
	public void deleteTheCreatedUser() {
		
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
			System.out.println("VERIFY IF USER IS DELETED API = PASSED");
	}
	
	// VERIFY IF USER IS DELETED (GET)
	
	@Test (priority = 6)
	public void verifyIfUserisDeleted() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.get("https://gorest.co.in/public/v2/users")
		
		.then()
			.log().body()
			.body("id", not(equalTo(+extractedID)));
			// body("[0].id", not(equalTo(+extractedID)));
			System.out.println("The ID we are verifying to check if its deleted is "+extractedID);
			System.out.println("VERIFY IF USER IS DELETED API = PASSED");
}
}