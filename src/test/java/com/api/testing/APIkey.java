package com.api.testing;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class APIkey {
	
	@Test
	public void APIkeyAuth1() {
		
		given()
			
		.when()
			.get("http://api.openweathermap.org/geo/1.0/direct?q=Hyderabad&limit=5&appid=ed9b8ed0edc090919795def6f872aef8")
			
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(4000L))
			.log().body();
		}
	
	@Test
	public void APIkeyAuth2() {
		
		given()
			.pathParam("pathParam1", "/geo")
			.pathParam("pathParam2", "/1.0")
			.pathParam("pathParam3", "/direct")
			.queryParam("q", "Chennai")
			.queryParam("limit", "5")
			.queryParam("appid", "ed9b8ed0edc090919795def6f872aef8")
			
		.when()
			.get("http://api.openweathermap.org/{pathParam1}/{pathParam2}/{pathParam3}")
			
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(4000L))
			.log().body();
		}
}