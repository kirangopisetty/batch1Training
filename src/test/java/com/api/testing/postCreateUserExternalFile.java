package com.api.testing;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
public class postCreateUserExternalFile {
	
	@Test
	public void createUserAPI() throws FileNotFoundException {
		
		File file = new File(".\\src\\test\\resources\\payload.json"); // to locate the file
		FileReader fr = new FileReader(file); 	// open + read the file
		JSONTokener jt = new JSONTokener(fr);   // read the json file as byte streams
		JSONObject requestBody = new JSONObject(jt);	// read the json file as json scheme
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody.toString())
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