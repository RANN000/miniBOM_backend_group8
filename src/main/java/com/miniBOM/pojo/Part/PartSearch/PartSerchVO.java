package com.miniBOM.pojo.Part.PartSearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartSerchVO {
    String code;
    String name;
    String userId;
    String version;
    String defaultUnit;
    String category;
    Map<String, String> attributes;
}
