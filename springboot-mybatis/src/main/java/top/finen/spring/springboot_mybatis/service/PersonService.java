package top.finen.spring.springboot_mybatis.service;

import top.finen.spring.springboot_mybatis.bean.Person;

public interface PersonService {
    Boolean addOnePerson(Person person);

    Person getPersonInfoByIdCard(String idCard);

    String getPersonName(String idCard);

    Boolean updatePersonNameByIdCard(String idCard, String name);

    Boolean deletePersonByIdCard(String idCard);


}
