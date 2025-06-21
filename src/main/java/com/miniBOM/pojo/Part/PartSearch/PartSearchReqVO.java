package com.miniBOM.pojo.Part.PartSearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartSearchReqVO {
    String id;
    String name;
    String version;
    String iteration;
    List<Map<String,String>> extAttrs;
    List<Map<String,Map<String,String>>> clsAttrs;
}
