package com.miniBOM.mapper;

import com.miniBOM.pojo.Attribute;
import com.miniBOM.pojo.Classification;
import com.miniBOM.pojo.Result;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ClassificationMapper {
    public void add(Classification classification){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", classification);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Classification/create", paramMap, Result.class);
    }

    public void update(Classification classification){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", classification);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Classification/update", paramMap, Result.class);
    }

    public void delete(Classification classification){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", classification);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Classification/delete", paramMap, Result.class);
    }

    public List list(String searchKey){
        return null;
    }
}
