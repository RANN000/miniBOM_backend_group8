package com.miniBOM.pojo.ClassificationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListClassificationDto {
    Integer id;
    String name;
    Integer pageSize;
    Integer curPage;
}
