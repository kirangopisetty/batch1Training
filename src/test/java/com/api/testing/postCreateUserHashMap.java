package com.api.testing;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class postCreateUserHashMap {
	
	@Test
	public void createUserAPI() {
		
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", "Nourin");
		requestBody.put("gender", "female");
		requestBody.put("email","nourinstudent@api.com");
		requestBody.put("status","active");
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody)
			.post("https://gorest.co.in/public/v2/users")
		
		.then()
			.statusCode(201)
			// response time > 1 second but < 2 seconds
			.time(Matchers.both(Matchers.greaterThanOrEqualTo(1000L)).and(Matchers.lessThanOrEqualTo(2000L)))
			.log().status()
			.log().body()
			.header("Content-Type", "application/json; charset=utf-8");	
	}
}