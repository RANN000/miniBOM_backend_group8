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
    Long id;
    String name;
    String nameEn;
    String description;
    String descriptionEn;
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
