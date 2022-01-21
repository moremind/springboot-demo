package cn.moremind.spring.springbootmybatis.service.impl;


import cn.moremind.spring.springbootmybatis.entity.Person;
import cn.moremind.spring.springbootmybatis.mapper.PersonMapper;
import cn.moremind.spring.springbootmybatis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
