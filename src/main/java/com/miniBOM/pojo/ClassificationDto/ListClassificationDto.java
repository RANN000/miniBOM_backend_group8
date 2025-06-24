package com.miniBOM.pojo.ClassificationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListClassificationDto {
    Long code;
    String name;
    Integer pageSize;
    Integer curPage;
}
