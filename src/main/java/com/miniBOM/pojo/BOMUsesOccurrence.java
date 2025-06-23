package com.miniBOM.pojo;


import lombok.Data;

@Data
public class BOMUsesOccurrence {

    private Long id;
    private String referenceDesignator;//位号名称
    private BOMLink bomLink;
}
