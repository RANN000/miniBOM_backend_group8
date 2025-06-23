package com.miniBOM.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BOMLink {
    private Long id;
    private Long sequenceNumber;
    private BigDecimal quantity;
    private Long sourceID;
    private Long targetID;
}
