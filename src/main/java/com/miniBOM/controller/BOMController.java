package com.miniBOM.controller;

import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreatePartDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateVO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowDTO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowFatherVO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowVO;
import com.miniBOM.pojo.Bom.BOMDelete.BOMDeleteDTO;
import com.miniBOM.pojo.Bom.BOMShow.BOMShowVO;
import com.miniBOM.pojo.Bom.BOMUpdate.BOMUpdateDTO;
import com.miniBOM.pojo.Bom.BOMUpdate.BOMUpdateVO;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.BOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BOM")
public class BOMController {

    @Autowired
    private BOMService BOMService;

    @PostMapping("/create")
    public Result<BOMCreateVO> create(@RequestBody BOMCreateDTO bomCreateDTO) {
        BOMCreateVO bomCreateVO = BOMService.add(bomCreateDTO);
        return Result.success(bomCreateVO);
    }

    //展示所有子项
    @GetMapping("/show")
    public Result<List<BOMShowVO>> show(@RequestParam Long sourceId) {
        List<BOMShowVO> bomShowVOList = BOMService.show(sourceId);
        return Result.success(bomShowVOList);
    }

    @PostMapping("/createPart")
    public Result<BOMCreateVO> createPart(@RequestBody BOMCreatePartDTO bomCreatePartDTO) {
        BOMCreateVO bomCreateVO=BOMService.addPart(bomCreatePartDTO);
        return Result.success(bomCreateVO);
    }

    @PutMapping("/update")
    public Result<BOMUpdateVO> update(@RequestBody BOMUpdateDTO bomUpdateDTO) {
        System.out.println("bomUpdateDTO:"+bomUpdateDTO);
        BOMUpdateVO bomUpdateVO=BOMService.update(bomUpdateDTO);
        return Result.success(bomUpdateVO);
    }

    @DeleteMapping("/delete")
    public Result update(@RequestBody BOMDeleteDTO bomDeleteDTO) {

        BOMService.delete(bomDeleteDTO);
        return Result.success();
    }

    //展示所有父项
    @GetMapping("/showFather")
    public Result showFather(@RequestParam Long partId) {
        try {
            return BOMService.showFather(partId);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //展示完整bom，查找root
    @GetMapping("/showRoot")
    public Result showRoot(@RequestParam Long partId){
        try{
            return BOMService.showRoot(partId);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

}
