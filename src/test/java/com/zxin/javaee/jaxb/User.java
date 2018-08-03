package com.zxin.javaee.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"userName","role","menu"})
@XmlRootElement(name = "user")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class User implements Serializable {

	private static final long serialVersionUID = -2578137856006475390L;
	
	private String userName;
    private int age;
    private String role;
    private String bibi;
    private Menu menu;
    public User() {
    }

    public User(String userName, int age, String role, String bibi) {
        this.userName = userName;
        this.role = role;
        this.age = age;
        this.bibi = bibi;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlAttribute
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @XmlElement(nillable=true)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @XmlTransient
    public String getBibi() {
        return bibi;
    }

    public void setBibi(String bibi) {
        this.bibi = bibi;
    }

    @XmlElement
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", role='" + role + '\'' +
                ", menu=" + menu +
                '}';
    }
}