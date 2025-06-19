package com.miniBOM.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UpdateClassificationDto {
    Long ID;
    String name;
    String nameEn;
    String description;
    String descriptionEn;
    boolean disableFlag=false;
}
