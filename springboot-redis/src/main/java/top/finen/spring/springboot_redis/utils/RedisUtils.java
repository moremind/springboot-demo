package top.finen.spring.springboot_redis.utils;


import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 用于实现redis基础功能的组件(Redis basic function)
 *
 * @author finen
 * email: hefengen@hotmail.com
 */
@Component
public class RedisUtils {
    @Autowired
    StringRedisTemplate redisTemplate;

    //****************************key****************************//

    /**
     * 设置key的过期时间(set this key's expiration time)
     *
     * @param key     redis key
     * @param timeout expiration time
     * @return true: set successfully
     * false: set unsuccessfully
     */
    public Boolean expire(@NotNull String key, @NotNull long timeout) {
        if (timeout > 0) {
            return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
        return false;
    }

    /**
     * 获取key的过期时间(get the key's expiration time)
     *
     * @param key redis key, not null
     * @return return expiration time of this key
     */
    public Long getExpire(@NotNull String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断这个key值是否存在于redis中(query this key in redis)
     *
     * @param key redis key, not null
     * @return true: this key is exist
     * false: this key isn't exist
     */
    public Boolean hasKey(@NotNull String key) {
        return redisTemplate.hasKey(key);
    }

    //****************************String****************************//

    //****************************Hash****************************//

    //****************************List****************************//

    //****************************Set****************************//

    //****************************zSet****************************//

}


