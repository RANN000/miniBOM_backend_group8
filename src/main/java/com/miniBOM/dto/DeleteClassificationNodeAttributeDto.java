package com.miniBOM.dto;

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
