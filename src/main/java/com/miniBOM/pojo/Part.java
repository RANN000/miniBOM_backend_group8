package com.miniBOM.pojo;

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
    String source;
    String partType;
    master master;
    branch branch;
    String applicationId;

    //get
    String id;//版本id

    //listAllVersion,checkOut,checkIn,delete
    String masterId;

    //update
    //版本id，同get
    //修改属性，同add
    String modifier;



}
