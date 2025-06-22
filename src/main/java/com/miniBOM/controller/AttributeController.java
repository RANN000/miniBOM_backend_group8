package com.miniBOM.controller;

import com.miniBOM.pojo.AandCDto.CreateAttributeDto;
import com.miniBOM.pojo.AandCDto.DeleteAttributeDto;
import com.miniBOM.pojo.AandCDto.ListAttributeDto;
import com.miniBOM.pojo.AandCDto.UpdateAttributeDto;
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

    @GetMapping("/list")
    public Result<Pair> list(@RequestBody ListAttributeDto attributeDto, short pageSize, short curPage){
        return attributeService.list(attributeDto,pageSize,curPage);
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
