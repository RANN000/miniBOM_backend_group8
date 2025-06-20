package com.miniBOM.pojo.Part.PartCreate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartCreateDTO {

    String code;
    String name;
    String defaultUnit;
    String category;
    Map<String, String> attributes;
}
