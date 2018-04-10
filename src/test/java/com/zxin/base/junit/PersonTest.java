package com.zxin.base.junit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PersonTest{
	
	private personBean person = new personBean();
    @Category(personBean.class)
    @Test
    public void testGetAge(){
        int age = person.getAge();
        assertEquals(3, age);
    }
    @Category(personBean.class)
    @Test
    public void testGetName(){
        String name = person.getName();
        assertEquals("Willard", name);
    }
    @Category(PersonTest.class)
    @Test
    public void testTalk(){
        String message = person.talkTo("Jimy");
        assertNotNull(message);
    }
    @Category(PersonTest.class)
    @Test(timeout=200)
    public void testWalk(){
        person.walk();
    }
    
    class personBean{
    	private int age;
    	private String name;
    	private String message;
    	public String talkTo(String name){
    		return message;
    	}
    	public String walk(){
    		return message;
    	}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
    	
    }
}