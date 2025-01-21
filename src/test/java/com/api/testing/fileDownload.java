package com.api.testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class fileDownload {
	
	@Test
	public void fileDownloadFunction() throws IOException {
		
	Response response =	given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
	
	.when()
		.get("https://gorest.co.in/public/v2/users")
		.thenReturn();
	
	System.out.println("Response code : "+response.getStatusCode());
	Assert.assertEquals(response.getStatusCode(), 200);
	byte[] responseBytes = response.getBody().asByteArray(); // response body in byte[] format
	File file = new File("C:\\Users\\Kiran\\Downloads\\REST ASSURED TESTING\\getAPIresponse.json");
	Files.write(file.toPath(), responseBytes);
	}
}