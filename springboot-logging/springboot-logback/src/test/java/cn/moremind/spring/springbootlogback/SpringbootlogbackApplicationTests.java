package cn.moremind.spring.springbootlogback;

import cn.moremind.spring.springbootlogback.util.LogUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootlogbackApplicationTests {

    @Test
    void contextLoads() {
        LogUtil.info(this.getClass(), "this is info log");
    }

}
