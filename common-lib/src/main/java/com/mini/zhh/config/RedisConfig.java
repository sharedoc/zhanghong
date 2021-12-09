package com.mini.zhh.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description RedisTemplate
 * @Author zunqiaozhang
 * @Date 2021/11/12
 */
@Configuration
public class RedisConfig {

    /**
     * redis template
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置工厂连接
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置自定义序列化方式
        setSerializeConfig(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 设置序列化方式
     *
     * @param redisTemplate
     * @param redisConnectionFactory
     */
    private void setSerializeConfig(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory redisConnectionFactory) {
        // 对字符串采取普通的序列化方式，适用于key，因为我们一般采取简单字符串作为key
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 普通string类型的key采用，普通序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 普通hash类型的key
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // 解决查询缓存转化异常问题
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 普通的值采用jackson方式序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // hash类型的值
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 属性设置完成afterPropertiesSet就会被调用，可以对设置不成功的做一些默认处理
        redisTemplate.afterPropertiesSet();
    }

}
