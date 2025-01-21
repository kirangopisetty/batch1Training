package com.api.testing;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.json.JSONObject;

public class postCreateUserAssertCommands {
	
	Faker faker = new Faker();
	
	@Test
	public void createUserAPI() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", faker.name().firstName());
		requestBody.put("gender", faker.demographic().sex());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "active");
		
		Response response = given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody.toString())
			.post("https://gorest.co.in/public/v2/users");
		
		Assert.assertEquals(response.getStatusCode(),201);
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 201 Created");
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		
		System.out.println("Status code >>"+response.getStatusCode());
		System.out.println("Status line >>"+response.getStatusLine());
		System.out.println("Response time >>"+response.getTime());
		System.out.println("Response headers >>"+response.getHeaders());
		System.out.println("Response body >>"+response.getBody());
	}
}