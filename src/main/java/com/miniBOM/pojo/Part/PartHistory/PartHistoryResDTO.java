package com.miniBOM.pojo.Part.PartHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartHistoryResDTO {

    //大版本
    String version;
    //小版本
    String iteration;
    //最后更新时间
    String lastUpdateTime;
}
