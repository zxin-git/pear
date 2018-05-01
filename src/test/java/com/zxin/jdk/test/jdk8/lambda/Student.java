package com.zxin.jdk.test.jdk8.lambda;

public class Student {

	private Person person;

	public static Student print(Person person) {
		return new Student(person);
	}
	
	public Student(Person person) {
		this.person = person;
	}
	

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	
}
