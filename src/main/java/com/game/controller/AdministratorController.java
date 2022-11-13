package com.game.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.game.common.Result;
import com.game.entity.Administrator;
import com.game.service.AdministratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Administrator> login(@RequestBody Administrator administrator, HttpServletRequest request) {
//        密码加密处理
        String password = administrator.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(password);

        //        查询数据库
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", administrator.getUsername());
        Administrator employee1 = administratorService.getOne(queryWrapper);

//        判断是否查询到
        if (employee1 == null) {
            return Result.error("登录失败");
        }

//        判断密码是否正确
        if (!employee1.getPassword().equals(password)) {
            return Result.error("登录失败");
        }

        request.getSession().setAttribute("employee", employee1.getId());
        return Result.success(employee1);
    }
}
