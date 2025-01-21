package com.api.testing;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class patchUpdateUserExternalFile {
	
	@Test
	public void updateUserAPI() throws FileNotFoundException {
		
		File file = new File(".\\src\\test\\resources\\updatePayload.json");	// to locate the file
		FileReader fr = new FileReader(file);	// to open & read the located file
		JSONTokener jt = new JSONTokener(fr);	// to read the file contents in byte streams
		JSONObject requestBody = new JSONObject(jt);	// to read the file contents in JSON string format
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")

		.when()
			.body(requestBody.toString())
			.patch("https://gorest.co.in/public/v2/users/7603269")

		.then()
			.log().status()
			.log().all()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.header("Content-Type", "application/json; charset=utf-8")
			.time(Matchers.lessThan(3000L));
	}
}