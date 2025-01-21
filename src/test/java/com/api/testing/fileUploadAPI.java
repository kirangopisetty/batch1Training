package com.api.testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.io.File;


public class fileUploadAPI {
	
	@Test
	public void fileUpload() {
		File fileToUpload = new File("C:\\Users\\Kiran\\Downloads\\REST ASSURED TESTING\\getAPIresponse.json");
		
		Response response = given()
			.multiPart("file", fileToUpload, "multipart/form-data")
		
		.when()
			.post("https://the-internet.herokuapp.com/upload")
			.thenReturn();
		
		System.out.println("Response code: "+response.getStatusCode());
		System.out.println("Response body: "+response.prettyPrint());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}