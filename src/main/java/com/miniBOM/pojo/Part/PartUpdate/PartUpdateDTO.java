package com.miniBOM.pojo.Part.PartUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartUpdateDTO {
    String code;
    String name;
    Map<String, String> attributes;
}
