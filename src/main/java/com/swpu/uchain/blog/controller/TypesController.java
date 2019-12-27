package com.swpu.uchain.blog.controller;

import com.swpu.uchain.blog.accessctro.RoleControl;
import com.swpu.uchain.blog.enums.RoleEnum;
import com.swpu.uchain.blog.form.InsertTypesForm;
import com.swpu.uchain.blog.form.UpdateTypesForm;
import com.swpu.uchain.blog.service.TypesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hobo
 * @description
 */
@RestController
@RequestMapping("/types")
@Api(tags = "文章分类接口")
@CrossOrigin
public class TypesController {

    @Autowired
    private TypesService typesService;

    @RoleControl(role = RoleEnum.ADMIN)
    @ApiOperation("添加分类")
    @PostMapping(name = "添加分类",value = "/insert")
    public Object insertType(InsertTypesForm form) {
        return typesService.insertTypes(form);
    }

    @RoleControl(role = RoleEnum.ADMIN)
    @ApiOperation("更新分类")
    @PostMapping(name = "更新分类",value = "/update")
    public Object updateType(UpdateTypesForm form) {
        return typesService.updateTypes(form);
    }

    @RoleControl(role = RoleEnum.ADMIN)
    @ApiOperation("删除分类")
    @GetMapping(name = "删除分类",value = "/delete")
    public Object deleteType(Integer id) {
        return typesService.deleteTypes(id);
    }

    @ApiOperation("查询所有分类")
    @GetMapping(name = "查询所有分类",value = "/all")
    public Object selectAllType(){
        return typesService.selectAllTypes();
    }


}
