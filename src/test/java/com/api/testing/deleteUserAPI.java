package com.api.testing;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class deleteUserAPI {
	
	@Test
	public void deleteUser() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")

		.when()
			.delete("https://gorest.co.in/public/v2/users/7603267")
		
		.then()
			.statusCode(204)
			.log().status()
			.time(Matchers.greaterThan(500L));		
	}
}