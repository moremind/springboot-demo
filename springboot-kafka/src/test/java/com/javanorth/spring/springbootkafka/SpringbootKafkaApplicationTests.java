package com.javanorth.spring.springbootkafka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class SpringbootKafkaApplicationTests {

    @Test
    void contextLoads() {
        String[] a = {"aaa", "bbb"};
        ArrayList<String> arrayList = (ArrayList<String>) Arrays.asList(a);
        arrayList.stream().parallel().forEach(f->setNum("sa"));

    }

    private void setNum(String s) {
        System.out.println(s);
    }



}
