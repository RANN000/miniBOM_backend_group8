package com.miniBOM.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class CreateClassificationDto {
    String name;
    String nameEn;
    String description;
    String descriptionEn;
    String businessCode="string";
    boolean disableFlag=false;
    Map.Entry<Integer,String> parent;
}
