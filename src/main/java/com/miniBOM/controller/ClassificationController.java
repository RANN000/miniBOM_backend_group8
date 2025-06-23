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

@RestController
@RequestMapping("/category")
public class ClassificationController {
    @Autowired
    private ClassificationService service;

    @PostMapping("/create")
    public Result<OneClassificationVo> add(@RequestBody CreateClassificationDto classificationDto) {
        return service.add(classificationDto);
    }

    @GetMapping("/list")
    public Result<ListClassificationVo> list(@RequestBody ListClassificationDto classificationDto) throws JsonProcessingException {
        return service.list(classificationDto);
    }

    @GetMapping("/get/{id}")
    public Result<OneClassificationVo> getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PutMapping("/update")
    public Result<OneClassificationVo> update(@RequestBody UpdateClassificationDto classificationDto) {
        return service.update(classificationDto);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long deleteId) {
        return service.delete(deleteId);
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
}
