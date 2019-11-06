package top.finen.spring.springboot_init;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import top.finen.spring.springboot_init.entity.Person;

@SpringBootTest
class SpringbootInitApplicationTests {
    private Logger logger = LoggerFactory.getLogger(Logger.class);
    @Test
    void contextLoads() {
    }

    @Test
    public void testPerson() {
        Person person = Person.builder().name("xiaoming").address("beijing").age(12).build();
        logger.info(person.toString());
    }

}
