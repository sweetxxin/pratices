//package com.xxin.demo.restful.conf;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
//public class JedisConfig {
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//    /**
//     *
//     * @Title: getJedisPool
//     * @Description: 获取jedisPool
//     * @return
//     */
//    @Bean
//    public JedisPool getJedisPool() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        JedisPool pool = new JedisPool(config, host, port);
//        return pool;
//    }
//}
