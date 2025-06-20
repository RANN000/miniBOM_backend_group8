package com.miniBOM.pojo.Part.PartCreate;

import com.miniBOM.pojo.Part.Branch;
import com.miniBOM.pojo.Part.Master;
import com.miniBOM.pojo.Pair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartCreateReqDTO {

    String id;
    String name;
    String defaultUnit;
//    Pair[] extAttrs;
    List<Pair> extAttrs;
    Master master;
    Branch branch;
}
