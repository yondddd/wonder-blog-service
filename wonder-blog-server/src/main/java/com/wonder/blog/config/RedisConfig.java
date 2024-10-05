package com.wonder.blog.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.wonder.common.utils.env.env.Environment;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.TimeoutOptions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        String hostname = Environment.getProperty("HOSTNAME");
        String host;
        String password;
        if (StringUtils.isNotBlank(hostname)) {
            host = "172.27.234.224";
            password = "redisdddd";
        } else {
            host = "192.168.56.10";
            password = "1234dddd";
        }
        config.setHostName(host);
        config.setPort(6379);
        config.setPassword(password);
        config.setDatabase(0);
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(10000))
                .clientOptions(ClientOptions.builder()
                        .timeoutOptions(TimeoutOptions.enabled(Duration.ofMillis(10000)))
                        .build())
                .build();

        return new LettuceConnectionFactory(config, clientConfig);
    }

    /**
     * 使用JSON序列化方式
     *
     * @return
     */
    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate() {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        FastJsonRedisSerializer<Object> jackson2JsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

}
