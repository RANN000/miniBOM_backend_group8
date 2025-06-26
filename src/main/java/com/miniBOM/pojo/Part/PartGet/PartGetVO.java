package com.miniBOM.pojo.Part.PartGet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartGetVO {
    String code;
    String name;
    String defaultUnit;
    String version;
    String category;
//    Map<String, String> attributes;
}
