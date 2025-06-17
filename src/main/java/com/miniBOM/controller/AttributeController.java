package com.miniBOM.controller;

import com.miniBOM.pojo.Attribute;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    @PostMapping
    public Result add(@RequestBody Attribute attribute) {
        attributeService.add(attribute);
        return Result.success();
    }

    @GetMapping
    public Result<List<Attribute>> list(String searchKey){
        List<Attribute> at =attributeService.list(searchKey);
        return Result.success(at);
    }

    @PutMapping
    public Result update(@RequestBody Attribute attribute) {
        attributeService.update(attribute);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestBody Attribute attribute) {
        attributeService.delete(attribute);
        return Result.success();
    }
}
