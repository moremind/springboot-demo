package com.javanorth.spring.springbootmybatis.service;


import com.javanorth.spring.springbootmybatis.entity.Person;

public interface PersonService {
    /**
     * add person
     * @param person person information
     * @return true/false
     */
    Boolean addOnePerson(Person person);

    /**
     * get person information by id
     * @param idCard idCard
     * @return person information
     */
    Person getPersonInfoByIdCard(String idCard);

    /**
     * get person name by id
     * @param idCard idCard
     * @return username
     */
    String getPersonName(String idCard);

    /**
     * update username by id
     * @param idCard id
     * @param name username
     * @return true/false
     */
    Boolean updatePersonNameByIdCard(String idCard, String name);

    /**
     * delete person by id
     * @param idCard id
     * @return true/false
     */
    Boolean deletePersonByIdCard(String idCard);


}
