package com.miniBOM.pojo.Part.PartHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartHistoryVO {

    //大版本+小版本
    String version;
    //更新时间
    String updatedAt;
}
