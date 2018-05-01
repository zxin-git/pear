package com.zxin.jdk.test.jdk8.function;
public class User {  
  
    private String name;  
  
    private String age;  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getAge() {  
        return age;  
    }  
  
    public void setAge(String age) {  
        this.age = age;  
    }  
      
    /** 
     * 静态方法 
     * @param car 
     */  
    public static void collide(final User user) {  
  
        System.out.println("Collided " + user.getName());  
  
    }  
  
    public void follow(final User another) {  
  
        System.out.println("Following the " + another.getName());  
  
    }  
  
    public void repair() {  
        System.out.println("Repaired " + this.toString());  
  
    }  
  
}  