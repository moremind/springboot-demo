package com.javanorth.spring.springbootmultidatasource.dao.primary;


import com.javanorth.spring.springbootmultidatasource.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * mysql数据源数据库操作语句
 */
@Mapper
@Component
public interface PrimaryDao {

    boolean insert(Person person);

}
