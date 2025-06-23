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
public class ListClassificationVo {
    private Integer number;
    private List<OneClassificationVo> list;
}
