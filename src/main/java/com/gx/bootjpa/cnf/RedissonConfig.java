package com.gx.bootjpa.cnf;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gx
 * @ClassName: RedissionConfig
 * @Description: java类作用描述
 * @date 2019/8/14 22:30
 * @Version: 1.0
 * @since
 */


    /**
     * redisson 配置类
     * Created on 2018/6/19
     */
    @Configuration
    public class RedissonConfig {

        @Value("${spring.redis.host}")
        private String host;

        @Value("${spring.redis.port}")
        private String port;

        @Bean
        public RedissonClient getRedisson(){

            Config config = new Config();
            config.useSingleServer().setAddress("redis://" + host + ":" + port);
            //添加主从配置
//        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});

            return Redisson.create(config);
        }

    }

