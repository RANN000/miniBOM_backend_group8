package com.miniBOM.pojo.Part.PartSearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartSearchDTO {
    String code;
    String name;
    String category;
}
