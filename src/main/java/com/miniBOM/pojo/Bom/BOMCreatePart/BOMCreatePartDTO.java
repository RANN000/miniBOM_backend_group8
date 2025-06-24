package com.miniBOM.pojo.Bom.BOMCreate;

import com.miniBOM.pojo.Part.PartCreate.PartCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BOMCreatePartDTO {

    //BOMLink的参数
    Long sourceId;
    Long targetId;
    Long sequenceNumber;
    BigDecimal quantity;
    String referenceDesignator;
    //创建子part的参数
    PartCreateDTO partCreateDTO;




}
