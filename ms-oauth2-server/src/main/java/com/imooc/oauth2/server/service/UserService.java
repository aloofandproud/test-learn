package com.imooc.oauth2.server.service;

import cn.hutool.core.bean.BeanUtil;
import com.imooc.commons.model.domain.SignInIdentity;
import com.imooc.commons.model.pojo.Diners;
import com.imooc.commons.utils.AssertUtil;
import com.imooc.oauth2.server.mapper.DinerMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserService implements UserDetailsService {
    @Resource
    private DinerMapper dinerMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AssertUtil.isNotEmpty(username,"请输入用户名");
        Diners diners = dinerMapper.selectByAccountInfo(username);
        if(diners==null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        //初始化登录认证对象
        SignInIdentity signInIdentity  = new SignInIdentity();
        BeanUtil.copyProperties(diners,signInIdentity);
        return signInIdentity;
//        return new User(username,diners.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(diners.getRoles()));
    }
}
