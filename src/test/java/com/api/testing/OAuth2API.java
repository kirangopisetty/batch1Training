package com.api.testing;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class OAuth2API {

	@Test
	public void OAuth2APIAuth() {
		
		given()
			.header("Accept", "application/json")
			.auth().oauth2("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")
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(4000L))
			.header("content-type", "application/json; charset=utf-8")
			.log().body();
	}
}