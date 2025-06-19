package com.miniBOM.controller;


import com.miniBOM.dto.CreateClassificationDto;
import com.miniBOM.dto.DeleteClassificationDto;
import com.miniBOM.dto.GetClassicificationDto;
import com.miniBOM.dto.UpdateClassificationDto;
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
    public Result add(@RequestBody CreateClassificationDto classificationDto) {
        return service.add(classificationDto);
    }

    @GetMapping
    public Result query(@RequestBody GetClassicificationDto classificationDto) {
        return service.query(classificationDto);
    }

    @PutMapping
    public Result update(@RequestBody UpdateClassificationDto classificationDto) {
        return service.update(classificationDto);
    }

    @DeleteMapping
    public Result delete(@RequestBody DeleteClassificationDto classificationDto) {
        return service.delete(classificationDto);
    }
}
