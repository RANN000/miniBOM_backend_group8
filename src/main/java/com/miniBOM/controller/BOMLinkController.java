package com.miniBOM.controller;

import com.miniBOM.pojo.BOMLink;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.BOMLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BOMLink")
public class BOMLinkController {

    @Autowired
    private BOMLinkService bomLinkService;

    @PostMapping("/create")
    public Result create(@RequestBody BOMLink bomLink) {
        try{
            return bomLinkService.create(bomLink);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
