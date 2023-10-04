package com.by.blog.controller;

import com.by.blog.form.CreatLeaveMsgForm;
import com.by.blog.service.LeaveMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hobo
 * @description
 */
@RestController
@RequestMapping("/leaveMsg")
@Api(tags = "留言接口")
@CrossOrigin
public class LeaveMsgController {

    @Autowired
    private LeaveMessageService messageService;

    @ApiOperation("添加新留言")
    @PostMapping(name = "添加新留言",value = "/insert")
    public Object insertLeaveMsg(CreatLeaveMsgForm form) {
        return messageService.insertLeaveMsg(form);
    }

    @ApiOperation("查看所有留言")
    @GetMapping(name = "查看所有留言",value = "/all")
    public Object selectAllLeaveMsg(){
        return messageService.selectAllLeaveMsg();
    }

}
