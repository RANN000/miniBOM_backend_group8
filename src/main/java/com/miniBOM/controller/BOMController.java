package com.miniBOM.controller;

import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateVO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowDTO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowVO;
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
        BOMCreateVO bomCreateVO=BOMService.add(bomCreateDTO);
        return Result.success(bomCreateVO);
    }

    @GetMapping("/show")
    public Result<List<BOMShowVO>> show(String code) {
        List<BOMShowVO> bomShowVOList=BOMService.show(code);
        return Result.success(bomShowVOList);
    }

}
