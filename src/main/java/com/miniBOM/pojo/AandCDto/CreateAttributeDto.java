package com.miniBOM.pojo.AandCDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateAttributeDto {
    String name;
    String nameEn;
    String description;
    String descriptionEn;
    String type="字符串定义";
    String businessCode="string";
    String constraint="string";
    String category="扩展属性";
    boolean disableFlag=false;
}
