package com.miniBOM.pojo.AttributeDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ListAttributeDto {
    Long id;
    String name;
    Integer pageSize;
    Integer curPage;
}
