package com.miniBOM.pojo.AandCDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CreateClassificationDto {
    String name;
    String nameEn;
    String description;
    String descriptionEn;
    String businessCode="string";
    boolean disableFlag=false;
    Map.Entry<Integer,String> parent;
}
