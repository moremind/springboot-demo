package com.javanorth.spring.springboot_redis.uitl;

import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
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

    /**
     * 移除key的过期时间
     * @param key key值
     * @return true or false
     */
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 将当前数据库的 key 移动到给定的数据库 db 当中
     * @param key key值
     * @param dbIndex 数据库索引
     * @return true or false
     */
    public Boolean move(String key, int dbIndex) {
        return redisTemplate.move(key, dbIndex);
    }

    /**
     * 获取随机的key值
     * @return 随机获得的key值
     */
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    /**
     * 删除key
     * @param key key值
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除key值
     * @param keys key的集合
     */
    public void deleteKeys(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 修改 key 的名称
     *
     * @param oldKey
     * @param newKey
     */
    public void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 仅当 newKey 不存在时，将 oldKey 改名为 newKey
     * @param oldKey 旧的key值
     * @param newKey 新的key值
     * @return true or false
     */
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 返回 key 所储存的值的类型
     * @param key key值
     * @return 返回key值的类型
     */
    public DataType type(String key) {
        return redisTemplate.type(key);
    }

    /**
     * 序列化key值
     * @param key key值
     * @return 序列化后的结果
     */
    public byte[] dump(String key) {
        return redisTemplate.dump(key);
    }

    //****************************String****************************//

    /**
     * 设置key
     * @param key key值
     * @param value value值
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取指定 key 的值
     * @param key key值
     * @return key对应的value值
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 返回 key 中字符串值的子字符
     * @param key key
     * @param start 开始位置
     * @param end 结束位置
     * @return 字串
     */
    public String getRange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * 设置当前key的value，并且返回旧的value值
     * @param key key值
     * @param value value值
     * @return 旧的value值
     */
    public String getAndSet(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 批量获取
     *
     * @param keys
     * @return
     */
    public List<String> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }
    //****************************Hash****************************//

    //****************************List****************************//

    //****************************Set****************************//

    //****************************zSet****************************//

}
