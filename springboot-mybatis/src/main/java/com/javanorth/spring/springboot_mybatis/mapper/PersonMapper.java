package com.javanorth.spring.springboot_mybatis.mapper;

import com.javanorth.spring.springboot_mybatis.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface PersonMapper {
    /**
     * add person
     * @param person person information
     * @return true: success
     *         false: fail
     */
    Boolean addPerson(Person person);

    /**
     * get one person by idCardNumber
     * @param idCardNumber idCard
     * @return person information
     */
    Person getOnePerson(String idCardNumber);

    /**
     * get one person's name by idCardNumber
     * @param idCardNumber icCard
     * @return person's name
     */
    String getPersonName(String idCardNumber);

    /**
     * update the person's name by idCard
     * @param idCardNumber idCard
     * @param name person's name
     * @return true: success
     *         false: fail
     */
    Boolean updatePersonName(String idCardNumber, String name);

    /**
     * delete one person by idCard
     * @param idCardNumber idCard
     * @return true: success
     *         false: fail
     */
    Boolean deletePerson(String idCardNumber);


}
