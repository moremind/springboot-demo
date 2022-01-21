package cn.moremind.spring.springbootmybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.moremind.spring.springbootmybatisplus.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserDetailDao extends BaseMapper<UserDetail> {
}
