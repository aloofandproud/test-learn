package com.immoc.diners.controller;

import com.immoc.diners.service.DinerService;
import com.imooc.commons.model.domain.ResultInfo;
import com.imooc.commons.model.dto.DinersDTO;
import com.imooc.commons.utils.ResultInfoUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "食客接口")
public class DinersController {
    @Resource
    private DinerService dinerService;
    @Resource
    private HttpServletRequest request;
    @GetMapping("signin")
    public ResultInfo signIn(String account, String password) {
        return dinerService.signIn(account, password, request.getServletPath());
    }

    @GetMapping("checkPhone")
    public ResultInfo checkPhone(String phone){
        System.out.println(11111);
        System.out.println(11111111);
        System.out.println(2222222);
        dinerService.checkPhoneIsRegisterd(phone);
        return ResultInfoUtil.buildSuccess(request.getServletPath());
    }
    @PostMapping("register")
    public ResultInfo register(@RequestBody DinersDTO dinersDTO){
        return dinerService.register(dinersDTO,request.getServletPath());
    }
}
