package com.api.testing;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class OAuth1API {
	
	@Test
	public void OAuth1APIAuth() {
		
		given()
			.header("Accept", "application/json")
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")
		//	.auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken", "signature")
		
		.when()
			.get("OAuth1 API URL")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(4000L))
			.header("content-type", "application/json")
			.log().body();
	}
}