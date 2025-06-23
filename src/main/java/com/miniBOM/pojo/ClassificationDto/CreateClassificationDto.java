package com.miniBOM.pojo.ClassificationDto;

import com.miniBOM.pojo.Pair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateClassificationDto {
    String name;
    String nameEn;
    String description;
    String descriptionEn;
    String businessCode="string";
    boolean disableFlag=false;
    boolean instantiable=true;
    Long parentId;

    public Boolean getDisableFlag() {
        return disableFlag;
    }

    public Boolean getIsInstantiable() {
        return instantiable;
    }
}
