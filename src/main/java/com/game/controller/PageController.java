package com.game.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.game.common.Result;
import com.game.entity.Game;
import com.game.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequestMapping("/homepage")
@Api(tags = "页面信息")
public class PageController {
    private final GameService gameService;

    @Autowired
    public PageController(GameService gameService){
        this.gameService = gameService;
    }
@RequestMapping(value="/page",method = RequestMethod.GET)
@ApiOperation(value = "普通的翻页")
    public Result<Page<Game>> pageControl(int pagenum){
      Page<Game> page =  gameService.page(new Page<>(pagenum,10));
      if(page.getPages()<pagenum){
          return Result.error("出错啦");
      }else {
          return Result.success(page);
      }
    }
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ApiOperation(value="模糊查询")
    public Result<Page<Game>> search(String gameName){
        Page<Game> page = gameService.page(new Page<>(1,10));
        LambdaQueryWrapper<Game> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(gameName),Game::getGameName,gameName);
        gameService.page(page,wrapper);
        if(page.getPages()>0) {
            return Result.success(page);
        }else {
            return Result.error("没有查到信息");
        }
    }
    @RequestMapping(value = "/searchpage",method = RequestMethod.GET)
    @ApiOperation(value="模糊查询带翻页功能")
    public Result<Page<Game>> searchPage(String gameName,int pagenum){
        Page<Game> page =  gameService.page(new Page<>(pagenum,10));
        LambdaQueryWrapper<Game> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(gameName),Game::getGameName,gameName);
        gameService.page(page,wrapper);
        if (page.getPages()<=pagenum) {
            return Result.success(page);
        }else{
            return Result.error("访问失败");
        }
    }
}
