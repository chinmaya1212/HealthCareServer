package com.HealthCare.HealthCare.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "UserAuth")
public class User {
    @Id

    private String email;
    private String name;
    private String password;
    private int money;

    public User(String email, String name, String password,int money) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.money=money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
