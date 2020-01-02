package top.finen.spring.springboot_mybatis.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.finen.spring.springboot_mybatis.entity.Person;
import top.finen.spring.springboot_mybatis.mapper.PersonMapper;
import top.finen.spring.springboot_mybatis.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;


    @Override
    public Boolean addOnePerson(Person person) {
        return personMapper.addPerson(person);
    }

    @Override
    public Person getPersonInfoByIdCard(String idCard) {
        return personMapper.getOnePerson(idCard);
    }

    @Override
    public String getPersonName(String idCard) {
        return personMapper.getPersonName(idCard);
    }

    @Override
    public Boolean updatePersonNameByIdCard(String idCard, String name) {
        return personMapper.updatePersonName(idCard, name);
    }

    @Override
    public Boolean deletePersonByIdCard(String idCard) {
        return personMapper.deletePerson(idCard);
    }
}
