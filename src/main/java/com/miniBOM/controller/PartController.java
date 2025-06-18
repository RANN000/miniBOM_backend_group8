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

    @PostMapping("/add")
    public Result add(@RequestBody Part part) {
        partService.add(part);
        return Result.success();
    }
    @PostMapping("/find")
    public Result find(@RequestBody Part part) {
        partService.find(part);
        return Result.success();
    }
    @PostMapping("/listAllVersion")
    public Result listAllVersion(@RequestBody Part part) {
        partService.listAllVersion(part);
        return Result.success();
    }

    @GetMapping
    public Result<List<Part>> list(String searchKey){
        List<Part> at =partService.list(searchKey);
        return Result.success(at);
    }


    @PutMapping
    public Result update(@RequestBody Part part) {
        partService.update(part);
        return Result.success();
    }


    @PostMapping("/checkOut")
    public Result checkOut(@RequestBody Part part){
        partService.checkOut(part);
        return Result.success();
    }

    @PostMapping("/checkIn")
    public Result checkIn(@RequestBody Part part){
        partService.checkIn(part);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestBody Part part) {
        partService.delete(part);
        return Result.success();
    }
}

