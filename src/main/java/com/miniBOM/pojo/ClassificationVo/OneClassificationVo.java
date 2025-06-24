package com.miniBOM.pojo.ClassificationVo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OneClassificationVo {
    private Long id;
    private String name;
    private String nameEn;
    private String description;
    private String descriptionEn;
    private Long parentId;
    private Boolean status;
    boolean isinstance=true;
}
