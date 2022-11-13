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

    /**
     * 管理员登录
     * @param administrator 管理员
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Administrator> login(@RequestBody Administrator administrator, HttpServletRequest request) {
        String password = administrator.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(password);

        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", administrator.getUsername());
        Administrator administratorServiceOne = administratorService.getOne(queryWrapper);

        if (administratorServiceOne == null) {
            return Result.error("账号错误或账号不存在");
        }

        if (!administratorServiceOne.getPassword().equals(password)) {
            return Result.error("密码错误");
        }

        request.getSession().setAttribute("administrator", administratorServiceOne.getId());
        return Result.success(administratorServiceOne);
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("administrators");
        return Result.success("退出成功");
    }
}
