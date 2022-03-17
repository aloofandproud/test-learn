package com.immoc.diners.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.imooc.commons.constant.RedisKeyConstant;
import com.imooc.commons.model.domain.ResultInfo;
import com.imooc.commons.model.dto.DinersDTO;
import com.imooc.commons.utils.AssertUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class SendVerifyCodeService {
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    public void send(String phone){
        AssertUtil.isNotNull(phone,"手机号码空了");
        if(!checkCodeIsExired(phone)){
            return;
        }
        String code = RandomUtil.randomNumbers(6);
        String key = RedisKeyConstant.verify_code.getKey()+phone;
        redisTemplate.opsForValue().set(key,code,60, TimeUnit.SECONDS);
    }

    private boolean checkCodeIsExired(String phone) {
        String key = RedisKeyConstant.verify_code.getKey()+phone;
        String code = redisTemplate.opsForValue().get(key);
        if(StrUtil.isBlank(code)){
            return true;
        }
        return false;
    }
    public String getCodeByPhone(String phone){
        String key = RedisKeyConstant.verify_code.getKey()+phone;
        String code = redisTemplate.opsForValue().get(key);
        return code;
    }

}
