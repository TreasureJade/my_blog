package com.by.blog.controller;

import com.by.blog.accessctro.RoleControl;
import com.by.blog.enums.RoleEnum;
import com.by.blog.form.InsertTagsForm;
import com.by.blog.form.UpdateTagsForm;
import com.by.blog.service.TagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hobo
 * @description
 */
@RestController
@RequestMapping("/tags")
@Api(tags = "文章标签接口")
@CrossOrigin
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @RoleControl(role = RoleEnum.ADMIN)
    @ApiOperation("添加标签")
    @PostMapping(name = "添加标签", value = "/insert")
    public Object insertTags(InsertTagsForm form) {
        return tagsService.insertTags(form);
    }

    @RoleControl(role = RoleEnum.ADMIN)
    @ApiOperation("更新标签")
    @PostMapping(name = "更新标签", value = "/update")
    public Object updateTags(UpdateTagsForm form) {
        return tagsService.updateTags(form);
    }

    @RoleControl(role = RoleEnum.ADMIN)
    @ApiOperation("删除标签")
    @GetMapping(name = "删除标签",value = "/delete")
    public Object deleteTags(Integer id) {
        return tagsService.deleteTags(id);
    }

    @ApiOperation("查看所有标签")
    @GetMapping(name = "查看所有标签",value = "/all")
    public Object selectAllTags(){
        return tagsService.selectAllTags();
    }

}
