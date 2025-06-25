package com.miniBOM.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;
import com.miniBOM.pojo.AttributeVo.OneAttributeVo;
import com.miniBOM.pojo.ClassificationDto.*;
import com.miniBOM.pojo.ClassificationVo.ListClassificationVo;
import com.miniBOM.pojo.ClassificationVo.OneClassificationVo;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.ClassificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理相关接口控制器
 * 提供分类的增删改查等功能
 */
@RestController
@RequestMapping("/category")
public class ClassificationController {

    @Autowired
    private ClassificationService service;

    /**
     * 创建分类
     * @param classificationDto 分类创建请求数据
     * @return 创建结果，包含分类详情
     */
    @PostMapping("/create")
    public Result<OneClassificationVo> add(@RequestBody CreateClassificationDto classificationDto) {
        return service.add(classificationDto);
    }

    /**
     * 获取分类列表
     * @param classificationDto 列表查询条件
     * @return 分类列表结果
     * @throws JsonProcessingException JSON处理异常
     */
    @GetMapping("/list")
    public Result<List<OneClassificationVo>> list() throws JsonProcessingException {
        ListClassificationDto classificationDto=new ListClassificationDto();
        classificationDto.setPageSize(1000);
        classificationDto.setCurPage(1);
        return service.list(classificationDto);
    }

    /**
     * 获取分类列表
     * @param classificationDto 列表查询条件
     * @return 分类列表结果
     * @throws JsonProcessingException JSON处理异常
     */
    @PostMapping("/query")
    public Result<List<OneClassificationVo>> query(ListClassificationDto classificationDto) throws JsonProcessingException {
        return service.list(classificationDto);
    }

    /**
     * 根据ID获取单个分类
     * @param id 分类ID
     * @return 分类详情结果
     */
    @GetMapping("/get/{id}")
    public Result<OneClassificationVo> getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    /**
     * 更新分类信息
     * @param classificationDto 分类更新请求数据
     * @return 更新结果，包含更新后的分类详情
     */
    @PutMapping("/update")
    public Result<OneClassificationVo> update(@RequestBody UpdateClassificationDto classificationDto) {
        return service.update(classificationDto);
    }

    /**
     * 根据ID删除分类
     * @param deleteId 分类ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long deleteId) {
        return service.delete(deleteId);
    }

//    @GetMapping("/getattribute")
//    public Result getAttribute(@PathVariable("id") Long id) {
//        return service.getAttribute(id);
//    }
}


//    @PostMapping("/add_attribute")
//    public Result addAttribute(@RequestBody AddClassificationNodeAttributeDto classificationNodeAttributeDto) {
//        return service.addAttribute(classificationNodeAttributeDto);
//    }
//
//    @DeleteMapping("delete_attribute")
//    public Result deleteAttribute(@RequestBody DeleteClassificationNodeAttributeDto classificationNodeAttributeDto) {
//        return service.deleteAttribute(classificationNodeAttributeDto);
//    }

