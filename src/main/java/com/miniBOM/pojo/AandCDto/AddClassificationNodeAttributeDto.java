package com.miniBOM.pojo.AandCDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddClassificationNodeAttributeDto {
    Integer holderId;
    List<Integer> attrIds;
}

//暂时弃用
