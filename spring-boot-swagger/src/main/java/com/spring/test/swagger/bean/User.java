package com.spring.test.swagger.bean;

import java.util.Date;

public class User {
    private int    id;
    private int    age;
    private String name     = "";
    private Date   birthday = new Date();

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", age=" + age + ", name=" + name + ", birthday=" + birthday
               + "]";
    }

}
