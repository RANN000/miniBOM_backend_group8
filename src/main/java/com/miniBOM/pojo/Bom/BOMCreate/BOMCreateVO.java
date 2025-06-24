package com.miniBOM.pojo.Bom.BOMCreate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BOMCreateVO {
    Long id;
    BigDecimal quantity;
    String sequenceNumber;
    String sourceId;
    String sourceName;
    String targetId;
    String targetName;
}
