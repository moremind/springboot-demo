package cn.moremind.spring.springbootmultidatasource.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class Person {
    private String name;
    private Integer age;
    private String cardNumber;
}
