package com.immoc.diners.controller;

import com.immoc.diners.service.SendVerifyCodeService;
import com.imooc.commons.model.domain.ResultInfo;
import com.imooc.commons.utils.ResultInfoUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class SendVerifyCodeController {
    @Resource
    private SendVerifyCodeService sendVerifyCodeService;
    @Resource
    private HttpServletRequest request;
    @GetMapping("send")
    public ResultInfo send(String phone){
        sendVerifyCodeService.send(phone);
        return ResultInfoUtil.buildSuccess("发送成功",request.getContextPath());
    }
}
