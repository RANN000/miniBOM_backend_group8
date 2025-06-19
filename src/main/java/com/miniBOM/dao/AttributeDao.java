package com.miniBOM.dao;

import com.miniBOM.dto.CreateAttributeDto;
import com.miniBOM.dto.DeleteAttributeDto;
import com.miniBOM.dto.GetAttributeDto;
import com.miniBOM.dto.UpdateAttributeDto;
import com.miniBOM.pojo.Result;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AttributeDao {
    public Result add(CreateAttributeDto attributeDto){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/basic/api/EXADefinition/create", attributeDto, Result.class);
        return null;
    }

    public Result update(UpdateAttributeDto attributeDto) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/basic/api/EXADefinition/update", attributeDto, Result.class);
        return null;
    }

    public Result delete(DeleteAttributeDto attributeDto){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/common/api/EXADefinition/delete", attributeDto, Result.class);
        return null;
    }

    public Result query(GetAttributeDto attributeDto) {
        RestTemplate restTemplate = new RestTemplate();
        return null;
    }
}
