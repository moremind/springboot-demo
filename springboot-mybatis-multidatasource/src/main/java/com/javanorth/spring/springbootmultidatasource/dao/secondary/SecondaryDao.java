package com.javanorth.spring.springbootmultidatasource.dao.secondary;

import com.javanorth.spring.springbootmultidatasource.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * sqlserver数据源数据库操作语句
 */
@Mapper
@Component
public interface SecondaryDao {

    boolean insert2(Person person);


}
