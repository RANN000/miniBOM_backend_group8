package com.miniBOM.pojo.AandCDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteClassificationNodeAttributeDto {
    List<Integer> linkIds;
}


//暂时弃用