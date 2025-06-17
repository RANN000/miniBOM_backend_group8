package com.miniBOM.controller;


import com.miniBOM.pojo.Attribute;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classification")
public class ClassificationController {
    @Autowired
    private ClassificationService service;

    @PostMapping
    public Result add(@RequestBody Attribute attribute) {
        service.add(attribute);
        return Result.success();
    }

    @GetMapping
    public Result<List<Attribute>> list(String searchKey){
        List<Attribute> at =service.list(searchKey);
        return Result.success(at);
    }

    @PutMapping
    public Result update(@RequestBody Attribute attribute) {
        service.update(attribute);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestBody Attribute attribute) {
        service.delete(attribute);
        return Result.success();
    }
}
