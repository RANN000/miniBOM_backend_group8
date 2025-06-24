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
public class BOMUpdateDTO {

    Long bomLinkId;
    Long sequenceNumber;
    BigDecimal quantity;
    String referenceDesignator;
}
