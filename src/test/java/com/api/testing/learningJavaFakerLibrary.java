package com.api.testing;

import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class learningJavaFakerLibrary {
	
	@Test
	public void fakerLibraryPractice() {
		
		Faker faker = new Faker();
		
		System.out.println("Random city name >> "+faker.address().cityName());
		System.out.println("Random full name >> "+faker.name().fullName());
		System.out.println("Random country capital name >> "+faker.country().capital());
		System.out.println("Random date >> "+faker.date().birthday());
		System.out.println("Random email id >> "+faker.internet().emailAddress());
	}
}