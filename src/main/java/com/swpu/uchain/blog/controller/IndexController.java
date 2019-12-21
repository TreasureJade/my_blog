package com.swpu.uchain.blog.controller;

import com.swpu.uchain.blog.accessctro.RoleControl;
import com.swpu.uchain.blog.enums.RoleEnum;
import com.swpu.uchain.blog.service.VisitorService;
import com.swpu.uchain.blog.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hobo
 * @description
 */
@RestController
@CrossOrigin
@Api(tags = "首页显示")
public class IndexController {

    @Autowired
    private VisitorService visitorService;

    @ApiOperation("获取首页底部信息")
    @PostMapping("/getMsg")
    public Object getMsg() {
        return visitorService.getIndexMsg();
    }

    @RoleControl(role = RoleEnum.ADMIN)
    @ApiOperation("/更新底部网站更新时间")
    @PostMapping("/updateTime")
    public Object updateTime(String date) {
        return ResultVOUtil.success(date);
    }

}
