package com.javanorth.spring.springbootjpa.dao;

import com.javanorth.spring.springbootjpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
@Repository
public interface UserDetailDao extends JpaRepository<Person, String> {

}
