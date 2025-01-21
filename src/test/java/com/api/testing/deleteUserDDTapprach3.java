package com.api.testing;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class deleteUserDDTapprach3 extends testDataForDDT {
	
	@Test (dataProvider="ddtDeleteUser")
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