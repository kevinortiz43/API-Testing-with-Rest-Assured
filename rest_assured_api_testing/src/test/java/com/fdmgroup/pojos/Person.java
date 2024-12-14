package com.fdmgroup.pojos;

import java.util.List;

public class Person {

    private int id;
    private String email;
    private String password;
    private double balance;
    private int age;
    private String name;
    private String company;
    private List<Friend> friends;

    public Person() {
        super();
    }

    public Person(int id, String email, String password, double balance, int age, String name, String company,
            List<Friend> friends) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.age = age;
        this.name = name;
        this.company = company;
        this.friends = friends;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    
    





}
