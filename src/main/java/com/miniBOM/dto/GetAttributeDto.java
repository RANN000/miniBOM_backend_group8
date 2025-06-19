package com.miniBOM.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class GetAttributeDto {
    short curPage;
    short pageSize;
    Short totalRows;
    Short totalPages;
    String name;
    String nameEn;
}
