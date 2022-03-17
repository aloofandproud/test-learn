//package com.imooc.oauth2.server.config;
//
//import com.imooc.commons.model.domain.SignInIdentity;
//import com.imooc.oauth2.server.service.UserService;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
//import javax.annotation.Resource;
//import java.util.LinkedHashMap;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfigurtion extends AuthorizationServerConfigurerAdapter {
//    @Resource
//    private ClientOAuth2DataConfiguration clientOAuth2DataConfiguration;
//    @Resource
//    private UserService userService;
//    @Resource
//    private AuthenticationManager authenticationManager;
//    @Resource
//    private TokenStore tokenStore;
//    @Resource
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.checkTokenAccess("permitAll()").checkTokenAccess("permitAll()");
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory().withClient(clientOAuth2DataConfiguration.getClientId())
//                .secret(passwordEncoder.encode(clientOAuth2DataConfiguration.getSecret()))
//                .refreshTokenValiditySeconds(clientOAuth2DataConfiguration.getRefreshTokenValidityTime())
//                .authorizedGrantTypes(clientOAuth2DataConfiguration.getGrantTypes())
//                .scopes(clientOAuth2DataConfiguration.getScopes());
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.userDetailsService(userService)
//                .authenticationManager(authenticationManager)
//                .tokenStore(tokenStore)
//                .tokenEnhancer((accesstoken,authentication)->{
////            //获取登录用户信息 然后设置
//           SignInIdentity signInIdentity = (SignInIdentity)authentication.getPrincipal();
//            LinkedHashMap<String,Object> map = new LinkedHashMap<>();
//            map.put("nickname",signInIdentity.getNickname());
//            map.put("avatarUrl",signInIdentity.getAvatarUrl());
//            DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken)accesstoken;
//            token.setAdditionalInformation(map);
//            return token;
//
//                    });
//    }
//}
