package com.game.controller;

import com.game.common.Result;
import com.game.entity.Contestant;
import com.game.service.ContestantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/contestant")
@Api(tags = "学生报名信息")
@CrossOrigin
public class ContestantController {
    @Autowired
    private ContestantService contestantService;
    /**
     * 学生报名
     * @param contestant 对象
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value="学生报名")
    public Result<String> addContestant(@RequestBody Contestant contestant){
       if(contestantService.save(contestant)){
           return Result.success("报名成功");
       }else {
           return Result.error("报名失败");
       }
    }
    /**
     *修改报名信息
     */
    @RequestMapping(value = "/change",method = RequestMethod.PUT)
    @ApiOperation(value="修改学生报名信息")
    public Result<String> changeContestant(@RequestBody Contestant contestant, HttpServletRequest request){
        if(request.getSession().getAttribute("administrator") == null){
            return Result.error("管理员未登录");
        }
        if(contestantService.updateById(contestant)){
            return Result.success("修改成功");
        }else {
            return Result.error("修改失败");
        }
    }
    /**
     * 删除报名信息
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除学生信息")
    public Result<String> deleteContestant(@RequestBody Contestant contestant, HttpServletRequest request){
        if (request.getSession().getAttribute("administrator") == null){
            return Result.error("管理员未登录");
        }
        if (contestantService.removeById(contestant.getId())){
            return Result.success("删除成功");
        }else {
            return Result.error("修改失败");
        }
    }
}
