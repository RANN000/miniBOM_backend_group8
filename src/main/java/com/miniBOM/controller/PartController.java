package com.miniBOM.controller;

import com.miniBOM.pojo.Attribute;
import com.miniBOM.pojo.Part;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/part")
public class PartController {

    @Autowired
    private PartService partService;

    @PostMapping
    public Result add(@RequestBody Part part) {
        partService.add(part);
        return Result.success();
    }

    @GetMapping
    public Result<List<Attribute>> list(String searchKey){
        List<Part> at =partService.list(searchKey);
        return Result.success(at);
    }

    @PutMapping
    public Result update(@RequestBody Part part) {
        partService.update(part);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestBody Part part) {
        partService.delete(part);
        return Result.success();
    }
}

