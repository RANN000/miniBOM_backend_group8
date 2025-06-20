package com.miniBOM.pojo.Part.PartUpdate;

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
public class PartUpdateReqDTO {

    //id是masterId
    String id;
    //"IAM2 91b433d47aec415fab90774eb9150549"
    //要求当前用户是该数据实例的修改者才可以修改
    String modifier;
    String name;
    String defaultUnit;
    //    Pair[] extAttrs;
    List<Pair> extAttrs;
    String applicationId;


}
