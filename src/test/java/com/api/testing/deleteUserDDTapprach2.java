package com.api.testing;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;

public class deleteUserDDTapprach2 {
	
	@DataProvider(name="ddt")
	public Object[] DDT() {
		
		return new Object[] {
					7621450,7621451,7621452,7621453
		};		
	}
	
	@Test (dataProvider="ddt")
	public void deleteUser(Object idToDelete) {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")

		.when()
			.delete("https://gorest.co.in/public/v2/users/"+idToDelete)
		
		.then()
			.statusCode(204)
			.log().status();
			System.out.println("ID deleted is >> "+idToDelete);
	}
}