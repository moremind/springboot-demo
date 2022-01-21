package cn.moremind.spring.springbootjpa.dao;

import cn.moremind.spring.springbootjpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
@Repository
public interface UserDetailDao extends JpaRepository<Person, String> {

}
