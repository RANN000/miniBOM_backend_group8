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

    @PostMapping
    public Result<Pair> add(@RequestBody CreateClassificationDto classificationDto) {
        return service.add(classificationDto);
    }

    @GetMapping
    public Result<Pair> get(@RequestBody GetClassicificationDto classificationDto) {
        return service.get(classificationDto);
    }

    @PutMapping
    public Result<Pair> update(@RequestBody UpdateClassificationDto classificationDto) {
        return service.update(classificationDto);
    }

    @DeleteMapping
    public Result<Pair> delete(@RequestBody DeleteClassificationDto classificationDto) {
        return service.delete(classificationDto);
    }
}
