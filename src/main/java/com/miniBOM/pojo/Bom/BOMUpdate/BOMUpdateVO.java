package com.miniBOM.pojo.Bom.BOMUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BOMUpdateVO {
    Long id;
    BigDecimal quantity;
    Long sequenceNumber;
    String referenceDesignator;
    Long sourceId;
    String sourceName;
    Long targetId;
    String targetName;

}
