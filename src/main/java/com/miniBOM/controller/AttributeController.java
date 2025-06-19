package com.miniBOM.controller;

import com.miniBOM.dto.CreateAttributeDto;
import com.miniBOM.dto.DeleteAttributeDto;
import com.miniBOM.dto.GetAttributeDto;
import com.miniBOM.dto.UpdateAttributeDto;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    @PostMapping("/create")
    public Result add(@RequestBody CreateAttributeDto attributeDto) {
        return attributeService.add(attributeDto);
    }

    @GetMapping("/query")
    public Result query(@RequestBody GetAttributeDto attributeDto){
        return attributeService.list(attributeDto);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateAttributeDto attributeDto) {
        return attributeService.update(attributeDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteAttributeDto attributeDto) {
        return attributeService.delete(attributeDto);
    }
}
