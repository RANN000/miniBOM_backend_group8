package com.miniBOM.dao;

import com.miniBOM.dto.CreateClassificationDto;
import com.miniBOM.dto.DeleteClassificationDto;
import com.miniBOM.dto.GetClassicificationDto;
import com.miniBOM.dto.UpdateClassificationDto;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClassificationDao {
    public Result<Pair> add(CreateClassificationDto classificationDto){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/basic/api/ClassificationNode/create", classificationDto, Result.class);
        return null;
    }

    public Result<Pair> update(UpdateClassificationDto classificationDto){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/basic/api/ClassificationNode/update", classificationDto, Result.class);
        return null;
    }

    public Result<Pair> delete(DeleteClassificationDto classificationDto){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/basic/api/ClassificationNode/delete", classificationDto, Result.class);
        return null;
    }

    public Result<Pair> get(GetClassicificationDto classificationDto){
        RestTemplate restTemplate = new RestTemplate();
        return null;
    }
}
