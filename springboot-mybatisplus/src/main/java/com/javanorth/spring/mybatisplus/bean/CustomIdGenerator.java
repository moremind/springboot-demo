package com.javanorth.spring.mybatisplus.bean;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CustomIdGenerator implements IdentifierGenerator {

    private final AtomicLong al = new AtomicLong(1);

    @Override
    public Number nextId(Object entity) {
        String bizKey = entity.getClass().getName();
        MetaObject metaObject = SystemMetaObject.forObject(entity);
        String name = (String) metaObject.getValue("name");
        return al.getAndAdd(1);
    }

    @Override
    public String nextUUID(Object entity) {
        return UUID.randomUUID().toString();
    }
}
