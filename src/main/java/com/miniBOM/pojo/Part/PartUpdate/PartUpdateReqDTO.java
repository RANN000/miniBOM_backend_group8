package com.miniBOM.pojo.Part.PartUpdate;

import com.miniBOM.pojo.Pair;
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
public class PartUpdateReqDTO {

    //id是masterId
    String id;
    //"IAM2 91b433d47aec415fab90774eb9150549"
    //要求当前用户是该数据实例的修改者才可以修改
    String modifier="IAM2 91b433d47aec415fab90774eb9150549";
    String name;
    String defaultUnit;

    //用来更改分类
    List<Pair> extAttrs;
    List<Map<String,Map<String,String >>> clsAttrs;
    String applicationId;


}
