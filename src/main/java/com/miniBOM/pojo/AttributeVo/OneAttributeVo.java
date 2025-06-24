package com.miniBOM.pojo.AttributeVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OneAttributeVo {
    private Long id;
    private String name;
    private String nameEn;
    private String description;
    private String descriptionEn;

    private String type;
    private Boolean disableFlag;

}