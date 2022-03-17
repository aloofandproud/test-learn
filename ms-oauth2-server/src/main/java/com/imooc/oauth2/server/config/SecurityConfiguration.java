//package com.imooc.oauth2.server.config;
//
//import cn.hutool.crypto.digest.DigestUtil;
//import com.imooc.oauth2.server.service.UserService;
//import org.bouncycastle.crypto.digests.MD5Digest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//import javax.annotation.Resource;
//import java.util.UUID;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    //注册redis工厂
//    @Resource
//    private RedisConnectionFactory redisConnectionFactory;
//    @Resource
//    private UserService userService;
//
//    @Bean
//    public TokenStore tokenStore(){
//        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//        tokenStore.setAuthenticationKeyGenerator(oAuth2Authentication-> UUID.randomUUID().toString());
//        return tokenStore;
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//
//                return DigestUtil.md5Hex(String.valueOf(rawPassword));
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String s) {
//                return DigestUtil.md5Hex(rawPassword.toString()).equals(DigestUtil.md5Hex(String.valueOf(s)));
//            }
//        };
//    }
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//    //  认证策略
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("oauth/**","/actuator/**").permitAll()
//                .and().authorizeRequests().anyRequest().authenticated()
//        .and().csrf().disable().httpBasic().disable();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }
//}
