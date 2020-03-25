package com.javanorth.spring.springboot_mybatis.entity;

public class Person {
    private String name;
    private Integer age;
    private String cardNumber;

    public Person() {
    }

    public Person(String name, Integer age, String cardNumber) {
        this.name = name;
        this.age = age;
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
