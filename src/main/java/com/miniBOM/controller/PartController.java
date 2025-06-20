package com.miniBOM.controller;

import com.miniBOM.pojo.Part.Part;
import com.miniBOM.pojo.Part.PartCreate.PartCreateVO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateDTO;
import com.miniBOM.pojo.Part.PartHistory.PartHistoryVO;
import com.miniBOM.pojo.Part.PartUpdate.PartUpdateDTO;
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
    //需求接口
    @PostMapping("/create")
    public Result create(@RequestBody PartCreateDTO partCreateDTO) {
        PartCreateVO partCreateVO=partService.add(partCreateDTO);
        return Result.success(partCreateVO);
    }
    @PostMapping("/find")
    public Result<Part> find(@RequestBody Part part) {
        Part p=partService.find(part);
        return Result.success(p);
    }
    //需求接口
    @GetMapping("/history")
    public Result<List<PartHistoryVO>> history(String code) {
        List<PartHistoryVO> historyVOS=partService.listAllVersion(code);
        return Result.success(historyVOS);
    }

    @GetMapping("/list")
    public Result<List<Part>> list(String searchKey){
        List<Part> p =partService.list(searchKey);
        return Result.success(p);
    }

    ////需求接口
    @PutMapping("/update")
    public Result update(@RequestBody PartUpdateDTO partUpdateDTO) {
        partService.update(partUpdateDTO);
        return Result.success();
    }


//    @PostMapping("/checkOut")
//    public Result checkOut(@RequestBody Part part){
//        partService.checkOut(part);
//        return Result.success();
//    }
//
//    @PostMapping("/checkIn")
//    public Result checkIn(@RequestBody Part part){
//        partService.checkIn(part);
//        return Result.success();
//    }


    @DeleteMapping("/delete")
    public Result delete(String code) {
        partService.delete(code);
        return Result.success("删除成功");
    }
}

