package com.javanorth.spring.springbootshiro.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
public class Role {

    private int id;
    private String name;

}
