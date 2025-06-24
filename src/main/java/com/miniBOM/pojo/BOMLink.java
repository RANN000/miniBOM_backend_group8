package com.miniBOM.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BOMLink {
    private Long id;
    private Long sequenceNumber;
    private String referenceDesignator;//位号名称
    private BigDecimal quantity;
    private Long sourceID;
    private Long targetID;
}
