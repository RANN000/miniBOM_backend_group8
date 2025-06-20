package com.miniBOM.pojo.Part.PartCreate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartCreateVO {
    //华为云创建part的返回对象
    String id;
}
