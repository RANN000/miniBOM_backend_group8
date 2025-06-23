package com.miniBOM.pojo.AttributeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateAttributeDto {

    //属性id
    private Long id;

    //改不了
    //属性中文名
    private String name;

    //改不了
    //属性英文名
    private String nameEn;

    //属性中文描述
    private String description;

    //属性英文描述
    private String descriptionEn;

    //属性状态
    private Boolean disableFlag;
}
