package com.miniBOM.controller;

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
    public Result<Part> find(@RequestBody Part part) {
        Part p=partService.find(part);
        return Result.success(p);
    }
    @PostMapping("/listAllVersion")
    public Result<List<Part>> listAllVersion(@RequestBody Part part) {
        List<Part> p=partService.listAllVersion(part);
        return Result.success(p);
    }

    @GetMapping("/list")
    public Result<List<Part>> list(String searchKey){
        List<Part> p =partService.list(searchKey);
        return Result.success(p);
    }


    @PutMapping("/update")
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

    @DeleteMapping("/delete")
    public Result delete(@RequestBody Part part) {
        partService.delete(part);
        return Result.success();
    }
}

