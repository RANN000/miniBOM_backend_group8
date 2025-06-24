package com.miniBOM.pojo.ClassificationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateClassificationDto {
    Long code;
    String name="未定义";
    String nameEn="undefined";
    String description="undefined";
    String descriptionEn="undefined";
    String businessCode="string";
    boolean status=true;
    boolean isInstance=true;
    Long parentId;

    public boolean getStatus(){
        return status;
    }

    public Boolean getIsInstance() {
        return isInstance;
    }
}
