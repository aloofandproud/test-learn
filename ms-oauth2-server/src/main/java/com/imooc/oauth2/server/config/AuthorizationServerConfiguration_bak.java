package com.imooc.oauth2.server.config;

import com.imooc.commons.model.domain.SignInIdentity;
import com.imooc.oauth2.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

//授权服务
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration_bak extends AuthorizationServerConfigurerAdapter {
    @Resource
    private ClientOAuth2DataConfiguration clientOAuth2DataConfiguration;
    //密码编码器
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisTokenStore redisTokenStore;

    //登录验证
    @Resource
    private UserService userService;
//    配置令牌端点约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许访问token的公钥，默认/oauth/token_key是受保护的
        security.tokenKeyAccess("permitAll()")
//                允许检查token的状态
                .checkTokenAccess("permitAll()");

    }
//客户端配置
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientOAuth2DataConfiguration.getClientId())
                .secret(passwordEncoder.encode(clientOAuth2DataConfiguration.getSecret()))
                //授权类型
        .authorizedGrantTypes(clientOAuth2DataConfiguration.getGrantTypes())
                //有效时间
                .accessTokenValiditySeconds(clientOAuth2DataConfiguration.getTokenValidityTime())
                //刷新时间
                .refreshTokenValiditySeconds(clientOAuth2DataConfiguration.getRefreshTokenValidityTime())
                .scopes(clientOAuth2DataConfiguration.getScopes());//客户端访问范围

    }
//配置授权以及令牌的访问端点和令牌服务
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                //具体登录方法
                .userDetailsService(userService)
                //token存储方法
                .tokenStore(redisTokenStore)
                //令牌增强对象，增强返回结果
        .tokenEnhancer((accesstoken,authentication)->{
            //获取登录用户信息 然后设置
           SignInIdentity signInIdentity = (SignInIdentity)authentication.getPrincipal();
            LinkedHashMap<String,Object> map = new LinkedHashMap<>();
            map.put("nickname",signInIdentity.getNickname());
            map.put("avatarUrl",signInIdentity.getAvatarUrl());
            DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken)accesstoken;
            token.setAdditionalInformation(map);
            return token;

        });
    }
}
