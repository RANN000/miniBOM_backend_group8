package com.miniBOM.controller;

import com.miniBOM.pojo.BOMUsesOccurrence;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.BOMUsesOccurrenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BOMUsesOccurrence")
public class BOMUsesOccurrenceController {

    @Autowired
    private BOMUsesOccurrenceService bomUsesOccurrenceService;

    //TODO 有效性验证

    @PostMapping("/create")
    public Result create(@RequestBody BOMUsesOccurrence bomUsesOccurrence) {
        try {
            return bomUsesOccurrenceService.create(bomUsesOccurrence);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
