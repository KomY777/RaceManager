package com.game.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.game.common.Result;
import com.game.entity.Game;
import com.game.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    /**
     * 添加赛事
     * @param game 比赛
     */
    @RequestMapping(value = "/gameMsg", method = RequestMethod.POST)
    public Result<String> addGameMsg(@RequestBody Game game, HttpServletRequest request) {
        log.info("请求成功发送");
//        判断是否是管理员登录
        if (request.getSession().getAttribute("administrator") == null)
            return Result.error("管理员未登录");

        gameService.save(game);
        return Result.success("添加成功");
    }


    /**
     * 查看比赛详情
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Game> getGameByName(@PathVariable Integer id, HttpServletRequest request) {
        if (request.getSession().getAttribute("administrator") == null)
            return Result.error("管理员未登录");

        QueryWrapper<Game> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Game gameServiceOne = gameService.getOne(queryWrapper);
        if (gameServiceOne == null)
            return Result.error("该比赛不存在");
        return Result.success(gameServiceOne);
    }
}
