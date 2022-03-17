package com.imooc.oauth2.server.config;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

//security配置类
@Configuration
@EnableWebSecurity
public class SecurityConfiguration_bak extends WebSecurityConfigurerAdapter {
    //注入redis连接工厂
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    //初始化redistokenstore 用于将token存储到redis
    @Bean
    public RedisTokenStore redisTokenStore(){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        //设置key的层级前缀，方便查询
        redisTokenStore.setPrefix("Token:");
        return redisTokenStore;
    }
    //初始化密码编辑器，用MD5加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return DigestUtil.md5Hex(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return DigestUtil.md5Hex(String.valueOf(rawPassword)).equals(encodedPassword);
            }
        };
    }
    //初始化管理对象
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    //放行认证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/oauth/**","/actuator/**").permitAll()
                .and()
//                其他请求必须认证
                .authorizeRequests().anyRequest().authenticated();
    }
}
