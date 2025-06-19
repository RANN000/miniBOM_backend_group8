package com.miniBOM.dao;

import com.miniBOM.dto.CreateAttributeDto;
import com.miniBOM.dto.DeleteAttributeDto;
import com.miniBOM.dto.GetAttributeDto;
import com.miniBOM.dto.UpdateAttributeDto;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class AttributeDao {
    public Result<Pair> add(CreateAttributeDto attributeDto){
        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                    "publicservices/rdm/basic/api/EXADefinition/create";

            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 封装请求体
            HttpEntity<CreateAttributeDto> request = new HttpEntity<>(attributeDto, headers);

            // 使用 ParameterizedTypeReference 保留泛型信息
            ResponseEntity<Result<Pair>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<Result<Pair>>() {}
            );

            return response.getBody();

        } catch (RestClientException e) {
            return Result.error("请求失败：" + e.getMessage());
        }
    }

    public Result<Pair> update(UpdateAttributeDto attributeDto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/basic/api/EXADefinition/update";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UpdateAttributeDto> request = new HttpEntity<>(attributeDto, headers);

        try {
            ResponseEntity<Result<Pair>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<Result<Pair>>() {}
            );

            return response.getBody();
        } catch (RestClientException e) {
            return Result.error("更新失败：" + e.getMessage());
        }
    }

    public Result<Pair> delete(DeleteAttributeDto attributeDto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/common/api/EXADefinition/delete";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DeleteAttributeDto> request = new HttpEntity<>(attributeDto, headers);

        try {
            ResponseEntity<Result<Pair>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<Result<Pair>>() {}
            );

            return response.getBody();
        } catch (RestClientException e) {
            return Result.error("删除失败：" + e.getMessage());
        }
    }

    public Result<Pair> get(GetAttributeDto attributeDto, short pageSize, short curPage) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // 构建带路径参数的URL
            String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                    "publicservices/rdm/basic/api/EXADefinition/find/{pageSize}/{curPage}";

            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 将DTO对象作为请求体（API要求将查询条件放在body中）
            HttpEntity<GetAttributeDto> requestEntity = new HttpEntity<>(attributeDto, headers);

            // 发送请求并指定路径参数
            ResponseEntity<Result<Pair>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<Result<Pair>>() {},
                    pageSize,  // 替换路径中的{pageSize}
                    curPage    // 替换路径中的{curPage}
            );

            return response.getBody();
        } catch (RestClientException e) {
            return Result.error("查询失败：" + e.getMessage());
        }
    }
}
