package com.miniBOM.pojo.Part;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Part {
    //add
    String id;
//    String source;
//    String partType;
    String name;

    Master master;
    Branch branch;
    String applicationId;

    //get
//    String id;//版本id

    //listAllVersion,checkOut,checkIn,delete
    String masterId;

    //update
    //版本id，同get
    //修改属性，同add
    String modifier;



}
