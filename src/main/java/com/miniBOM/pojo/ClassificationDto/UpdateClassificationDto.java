package com.miniBOM.pojo.ClassificationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateClassificationDto {
    Long ID;
    String name;
    String nameEn;
    String description;
    String descriptionEn;
    boolean disableFlag=false;
}
