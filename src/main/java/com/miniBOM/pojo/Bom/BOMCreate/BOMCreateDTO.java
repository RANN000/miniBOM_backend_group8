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
public class BOMCreateDTO {

    Long sourceId;
    Long targetId;
    String sequenceNumber;
    BigDecimal quantity;


}
