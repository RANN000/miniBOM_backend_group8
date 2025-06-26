package com.miniBOM.controller;

import com.miniBOM.pojo.Part.PartCreate.PartCreateVO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateDTO;
import com.miniBOM.pojo.Part.PartGet.PartGetVO;
import com.miniBOM.pojo.Part.PartHistory.PartHistoryVO;
import com.miniBOM.pojo.Part.PartSearch.PartSearchDTO;
import com.miniBOM.pojo.Part.PartSearch.PartSerchVO;
import com.miniBOM.pojo.Part.PartUpdate.PartUpdateDTO;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/part")
public class PartController {

    @Autowired
    private PartService partService;
    //需求接口，创建part
    @PostMapping("/create")
    public Result<PartCreateVO> create(@RequestBody PartCreateDTO partCreateDTO) {
        Map<String,Object> map=partService.add(partCreateDTO);
        return Result.success((PartCreateVO)map.get("partCreateVO"));
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
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        partService.delete(id);
        return Result.success("删除成功");
    }
    //需求接口，根据分类id提供分类可选属性
    @GetMapping("/attributeTemplate")
    public Result<List<String>> attributeTemplate(String category){
        List<String> p =partService.listCategoryAttr(category);
        return Result.success(p);
    }

    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable String id) {
        PartGetVO partGetVO=partService.detail(id);
        return Result.success(partGetVO);
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

