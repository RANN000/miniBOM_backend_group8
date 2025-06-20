package com.miniBOM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateAttributeDto {
    Long ID;
    String businessCode="string";
    String constraint="string";
    String name;
    String nameEn;
    String description;
    String descriptionEn;
    boolean disableFlag=false;
    String type="字符串定义";

}
