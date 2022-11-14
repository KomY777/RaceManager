package com.game.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.game.common.Result;
import com.game.entity.Game;
import com.game.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


@Slf4j
@RestController
@RequestMapping("/game")
@Api(tags = "比赛信息管理")
@CrossOrigin
public class GameController {

    @Value("${gameFile.path}")
    private String rootPath;

    @Autowired
    private GameService gameService;

    /**
     * 添加赛事
     * @param game 比赛
     */
    @RequestMapping(value = "/gameMsg", method = RequestMethod.POST)
    @ApiOperation(value = "添加比赛", notes = "仅管理员可操作")
    @ApiImplicitParam(name = "game", value = "比赛信息", required = true, paramType = "body")
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
    @ApiOperation(value = "根据id获取比赛详情", notes = "均可操作")
    public Result<Game> getGameById(@PathVariable Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("administrator") == null)
            return Result.error("管理员未登录");

        QueryWrapper<Game> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Game gameServiceOne = gameService.getOne(queryWrapper);
        if (gameServiceOne == null)
            return Result.error("该比赛不存在");
        return Result.success(gameServiceOne);
    }

    /**
     * 撤销比赛
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id撤销比赛", notes = "仅管理员可操作")
    public Result<String> deleteById(@PathVariable Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("administrator") == null)
            return Result.error("管理员未登录");

        QueryWrapper<Game> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Game gameServiceOne = gameService.getOne(queryWrapper);
        if (gameServiceOne == null)
            return Result.error("该比赛不存在");
        else {
            Game game = gameService.getOne(queryWrapper);
            File file = new File(rootPath + game.getGameFile());
            file.delete();
            gameService.remove(queryWrapper);
        }
        return Result.success("撤销成功");
    }
}
