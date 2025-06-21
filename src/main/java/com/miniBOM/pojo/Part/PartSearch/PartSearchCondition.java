package com.miniBOM.pojo.Part.PartSearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartSearchCondition {
    String conditionName;
    List<String> conditionValue;
}
