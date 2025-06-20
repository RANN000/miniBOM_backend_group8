package com.miniBOM.dao;

import com.miniBOM.dto.*;
import com.miniBOM.pojo.Pair;
import com.miniBOM.pojo.Result;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ClassificationDao {
//    public Result<Pair> add(CreateClassificationDto params){
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                "publicservices/rdm/basic/api/ClassificationNode/create", params, Result.class);
//
//    }
//
//    public Result<Pair> update(UpdateClassificationDto params){
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                "publicservices/rdm/basic/api/ClassificationNode/update", params, Result.class);
//
//    }
//
//    public Result<Pair> delete(DeleteClassificationDto params){
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                "publicservices/rdm/basic/api/ClassificationNode/delete", params, Result.class);
//
//    }
//
//    public Result<Pair> get(GetClassicificationDto params){
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                "publicservices/rdm/common/api/ClassificationNode/get",params,Result.class);
//    }


    public Result<Pair> add(CreateClassificationDto params) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/basic/api/ClassificationNode/create";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateClassificationDto> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<Result<Pair>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<Result<Pair>>() {}
            );

            return response.getBody();
        } catch (RestClientException e) {
            return Result.error("添加失败：" + e.getMessage());
        }
    }

    public Result<Pair> update(UpdateClassificationDto params) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/basic/api/ClassificationNode/update";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UpdateClassificationDto> request = new HttpEntity<>(params, headers);

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

    public Result<Pair> delete(DeleteClassificationDto params) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/basic/api/ClassificationNode/delete";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DeleteClassificationDto> request = new HttpEntity<>(params, headers);

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

    public Result<Pair> get(GetClassicificationDto params) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                "publicservices/rdm/common/api/ClassificationNode/get";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GetClassicificationDto> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<Result<Pair>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<Result<Pair>>() {}
            );

            return response.getBody();
        } catch (RestClientException e) {
            return Result.error("获取失败：" + e.getMessage());
        }
    }

    public Result<Pair> list(short pageSize,short curPage) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // 构建带路径参数的URL
            String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
                    "publicservices/rdm/common/api/ClassificationNode/list/{pageSize}/{curPage}";

            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 发送请求并指定路径参数
            ResponseEntity<Result<Pair>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
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