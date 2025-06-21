package com.miniBOM.controller;

import com.miniBOM.pojo.Part.PartCreate.PartCreateVO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateDTO;
import com.miniBOM.pojo.Part.PartHistory.PartHistoryVO;
import com.miniBOM.pojo.Part.PartSearch.PartSearchDTO;
import com.miniBOM.pojo.Part.PartSearch.PartSerchVO;
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
    //需求接口，创建part
    //TODO 修改拓展属性，从拓展属性加入分类属性
    @PostMapping("/create")
    public Result<PartCreateVO> create(@RequestBody PartCreateDTO partCreateDTO) {
        PartCreateVO partCreateVO=partService.add(partCreateDTO);
        return Result.success(partCreateVO);
    }

    //需求接口，根据条件查询part
    @PostMapping("/search")
    public Result<List<PartSerchVO>> search(@RequestBody PartSearchDTO partSearchDTO) {
        List<PartSerchVO> partSerchVOList=partService.find(partSearchDTO);
        return Result.success(partSerchVOList);
    }
    //需求接口，获取part历史版本对象
    @GetMapping("/history")
    public Result<List<PartHistoryVO>> history(String code) {
        List<PartHistoryVO> historyVOS=partService.listAllVersion(code);
        return Result.success(historyVOS);
    }

    ////需求接口，修改part
    @PutMapping("/update")
    public Result update(@RequestBody PartUpdateDTO partUpdateDTO) {
        partService.update(partUpdateDTO);
        return Result.success();
    }



    //需求接口，删除part
    @DeleteMapping("/delete")
    public Result delete(String code) {
        partService.delete(code);
        return Result.success("删除成功");
    }
    //需求接口，根据分类id提供分类可选属性
    @GetMapping("/attributeTemplate")
    public Result<List<String>> attributeTemplate(String categoryId){
        List<String> p =partService.listCategoryAttr(categoryId);
        return Result.success(p);
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

    //    @GetMapping("/list")
//    public Result<List<Part>> list(String searchKey){
//        List<Part> p =partService.list(searchKey);
//        return Result.success(p);
//    }

}

