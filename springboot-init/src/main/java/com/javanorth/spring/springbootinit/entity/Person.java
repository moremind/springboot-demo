package com.javanorth.spring.springbootinit.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Person {
    private String name;
    private String address;
    private Integer age;
}
