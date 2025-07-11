package com.miniBOM.pojo.Bom.BOMSearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BOMShowFatherVO {

    private Long BOMLinkId;
    private BigDecimal quantity;
    private Long sequenceNumber;
    private Long sourceId;
    private String sourceName;
    private String referenceDesignator;//位号名称
}