package com.api.testing;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.util.HashMap;

public class patchUpdateUserHashMap {
	
	@Test
	public void updateUserAPI() {
		
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", "Mr.YOGI");
		requestBody.put("email", "yogi@bjp.com");
		requestBody.put("status", "active");
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody)
			.patch("https://gorest.co.in/public/v2/users/7603269")
		
		.then()
			.log().status()
			.log().body()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.header("Content-Type", "application/json; charset=utf-8")
			.time(Matchers.lessThan(2000L));
	}
}