package com.api.testing;

import org.testng.annotations.DataProvider;
import com.github.javafaker.Faker;

public class testDataForDDT {
	
	Faker faker = new Faker();
	
	@DataProvider (name="ddtCreateUser")
	public Object[][] DDTCreateUser() {
	
		return new Object[][] {
		
			{faker.name().firstName(),faker.demographic().sex(),faker.internet().emailAddress(),"active"},
			{faker.name().firstName(),faker.demographic().sex(),faker.internet().emailAddress(),"active"},
			{faker.name().firstName(),faker.demographic().sex(),faker.internet().emailAddress(),"inactive"},
			{faker.name().firstName(),faker.demographic().sex(),faker.internet().emailAddress(),"inactive"},
			{faker.name().firstName(),faker.demographic().sex(),faker.internet().emailAddress(),"active"}
		};
	}

	
	@DataProvider(name="ddtDeleteUser")
	public Object[] DDTDeleteUser() {
		
		return new Object[] {
				7621473,7621472,7621471,7621470
		};		
	}
}