package com.miniBOM.controller;


import com.miniBOM.pojo.Attribute;
import com.miniBOM.pojo.Classification;
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
    public Result add(@RequestBody Classification classification) {
        service.add(classification);
        return Result.success();
    }

    @GetMapping
    public Result<List<Attribute>> list(String searchKey){
        List<Attribute> at =service.list(searchKey);
        return Result.success(at);
    }

    @PutMapping
    public Result update(@RequestBody Classification classification) {
        service.update(classification);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestBody Classification classification) {
        service.delete(classification);
        return Result.success();
    }
}
