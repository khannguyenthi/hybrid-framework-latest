package javaBasic;

import com.github.javafaker.Faker;

public class Topic_18_RamdomData {

	public static void main(String[] args) {
		Faker fake = new Faker();
		
		System.out.println(fake.name().firstName());
		System.out.println(fake.name().lastName());
		System.out.println(fake.internet().emailAddress());
		System.out.println(fake.internet().privateIpV4Address());
		System.out.println(fake.internet().publicIpV4Address());
		System.out.println(fake.finance().creditCard());
		
	}
}
