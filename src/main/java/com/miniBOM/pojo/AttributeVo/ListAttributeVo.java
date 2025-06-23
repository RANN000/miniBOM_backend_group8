package com.miniBOM.pojo.AttributeVo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListAttributeVo {
    private Integer number;
    private List<OneAttributeVo> list;
}
