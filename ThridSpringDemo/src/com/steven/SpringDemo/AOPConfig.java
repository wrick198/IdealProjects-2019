package com.steven.SpringDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy //开启AspectJ自动代理机制
@ComponentScan
public class AOPConfig {
    @Bean
    public Audience audience(){  //定义Audience的Bean
        return new Audience();
    }
}
