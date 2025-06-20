package com.miniBOM.controller;

import com.miniBOM.dto.CreateAttributeDto;
import com.miniBOM.dto.DeleteAttributeDto;
import com.miniBOM.dto.QueryAttributeDto;
import com.miniBOM.dto.UpdateAttributeDto;
import com.miniBOM.pojo.Pair;
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
    public Result<Pair> add(@RequestBody CreateAttributeDto attributeDto) {
        return attributeService.add(attributeDto);
    }

    @GetMapping("/query")
    public Result<Pair> query(@RequestBody QueryAttributeDto attributeDto, short pageSize, short curPage){
        return attributeService.query(attributeDto,pageSize,curPage);
    }

    @PutMapping("/update")
    public Result<Pair> update(@RequestBody UpdateAttributeDto attributeDto) {
        return attributeService.update(attributeDto);
    }

    @DeleteMapping("/delete")
    public Result<Pair> delete(@RequestBody DeleteAttributeDto attributeDto) {
        return attributeService.delete(attributeDto);
    }
}
