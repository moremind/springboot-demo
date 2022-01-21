package cn.moremind.spring.springbootmail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;

@SpringBootTest
class SpringbootMailApplicationTests {

    @Test
    void contextLoads() {

        String path = "D:\\Soft\\test.png";

        FileSystemResource file= new FileSystemResource(path);
        System.out.println(file.exists());
        System.out.println(file.getFilename());
    }

}
