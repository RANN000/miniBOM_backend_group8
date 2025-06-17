package com.miniBOM.mapper;

import com.miniBOM.pojo.Attribute;
import com.miniBOM.pojo.Result;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public class AttributeMapper {
    public void add(Attribute attribute){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", attribute);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Attribute/create", paramMap, Result.class);
    }

    public void update(Attribute attribute) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", attribute);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Attribute/update", paramMap, Result.class);
    }

    public void delete(Attribute attribute){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", attribute);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Attribute/delete", paramMap, Result.class);
    }

    public List<Attribute> list(String searchKey){
        int pageSize = 10;
        int curPage = 1;
        RestTemplate restTemplate=new RestTemplate();
        Result<List<Attribute>> result = restTemplate.getForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/BOMUsesOccurrence/query/{pageSize}/{curPage}", Result.class,pageSize,curPage);
        if (result != null) {
            return result.data;
        }
        return null;
    }
}
