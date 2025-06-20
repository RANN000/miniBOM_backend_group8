package com.miniBOM.controller;


import com.miniBOM.dto.CreateClassificationDto;
import com.miniBOM.dto.DeleteClassificationDto;
import com.miniBOM.dto.GetClassicificationDto;
import com.miniBOM.dto.UpdateClassificationDto;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classification")
public class ClassificationController {
    @Autowired
    private ClassificationService service;

    @PostMapping("/create")
    public Result<Pair> add(@RequestBody CreateClassificationDto classificationDto) {
        return service.add(classificationDto);
    }

    @GetMapping("/get")
    public Result<Pair> get(@RequestBody GetClassicificationDto classificationDto) {
        return service.get(classificationDto);
    }

    @GetMapping("/list")
    public Result<Pair> list(short pageSize,short curPage) {
        return service.list(pageSize,curPage);
    }

    @PutMapping("/update")
    public Result<Pair> update(@RequestBody UpdateClassificationDto classificationDto) {
        return service.update(classificationDto);
    }

    @DeleteMapping("/delete")
    public Result<Pair> delete(@RequestBody DeleteClassificationDto classificationDto) {
        return service.delete(classificationDto);
    }
}
