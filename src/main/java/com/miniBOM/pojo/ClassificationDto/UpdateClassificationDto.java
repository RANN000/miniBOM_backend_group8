package com.miniBOM.pojo.ClassificationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateClassificationDto {
    private Long id;
    private String name;
    private String nameEn;
    private String description;
    private String descriptionEn;
    private boolean status;
    private boolean isInstance;


    public boolean getIsInstance() {
        return isInstance;
    }

    public boolean getStatus() {
        return status;
    }
}
