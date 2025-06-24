package com.miniBOM.pojo.Bom.BOMSearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BOMShowVO {

    String BOMLinkId;
    String quantity;
    String sequenceNumber;
    String targetId;
    String targetName;

}
