package com.miniBOM.controller;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdsModifierDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionCreateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionUpdateDTO;
import com.miniBOM.pojo.AttributeDto.ListAttributeDto;
import com.miniBOM.pojo.AttributeVo.ListAttributeVo;
import com.miniBOM.pojo.AttributeVo.OneAttributeVo;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    /**
     * 创建新属性定义
     *
     * @param createRequest 创建请求DTO，包含属性定义的初始数据
     * @return 创建成功后的属性视图对象
     */
    @PostMapping("/create")
    public Result<OneAttributeVo> add(@RequestBody EXADefinitionCreateDTO createRequest){
        return attributeService.add(createRequest);
    }

    /**
     * 根据id查询属性
     * @param id 根据id
     * @return 查询成功后的属性视图对象
     */
    @GetMapping("/get/{id}")
    public Result<OneAttributeVo> getById(@PathVariable Long id) {
        return attributeService.getById(id);
    }


    /**
     * 分页查询属性定义列表
     *
     * @param attributeDto 包含页面信息及搜索关键词
     * @return 分页结果，包含属性列表和总记录数
     */
    @PostMapping({"/query"})
    public Result<ListAttributeVo> query(@RequestBody ListAttributeDto attributeDto) {
        return attributeService.list(attributeDto);

    }

    /**
     * 分页查询属性定义列表
     * @return 分页结果，包含属性列表和总记录数
     */
    @GetMapping({"/list"})
    public Result<ListAttributeVo> list() {
        ListAttributeDto attributeDto=new ListAttributeDto();
        attributeDto.setCurPage(1);
        attributeDto.setPageSize(1000);
        return attributeService.list(attributeDto);

    }

    /**
     * 更新属性定义
     *
     * @param updateRequest 更新请求DTO，包含属性定义的更新数据
     * @return 更新后的属性信息
     */
    @PutMapping("/update")
    public Result<OneAttributeVo> update(@RequestBody EXADefinitionUpdateDTO updateRequest) {
        return attributeService.update(updateRequest);
    }

    /**
     * 删除属性
     *
     * @param deleteId 待删除记录ID
     * @return 操作结果
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long deleteId) {
        return attributeService.delete(deleteId);
    }

    /**
     * 批量删除属性定义
     *
     * @param deleteRequest 包含待删除记录ID列表的请求对象
     * @return 操作结果
     */
    @DeleteMapping("/delete")
    public Result deleteAttributes(@RequestBody PersistObjectIdsModifierDTO deleteRequest) {

        // 返回无数据的成功响应
        return attributeService.deleteAttributes(deleteRequest);
    }
}

//@Component
//public class AttributeDao {
//    public Result<Pair> add(CreateAttributeDto params){
//
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//
//            String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                    "publicservices/rdm/basic/api/EXADefinition/create";
//
//            // 设置请求头
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            // 封装请求体
//            HttpEntity<CreateAttributeDto> request = new HttpEntity<>(params, headers);
//
//            // 使用 ParameterizedTypeReference 保留泛型信息
//            ResponseEntity<Result<Pair>> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    request,
//                    new ParameterizedTypeReference<Result<Pair>>() {}
//            );
//
//            return response.getBody();
//
//        } catch (RestClientException e) {
//            return Result.error("请求失败：" + e.getMessage());
//        }
//    }
//
//    public Result<Pair> update(UpdateAttributeDto params) {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                    "publicservices/rdm/basic/api/EXADefinition/update";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<UpdateAttributeDto> request = new HttpEntity<>(params, headers);
//
//
//            ResponseEntity<Result<Pair>> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    request,
//                    new ParameterizedTypeReference<Result<Pair>>() {}
//            );
//
//            return response.getBody();
//        } catch (RestClientException e) {
//            return Result.error("更新失败：" + e.getMessage());
//        }
//    }
//
//    public Result<Pair> delete(DeleteAttributeDto params) {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                    "publicservices/rdm/common/api/EXADefinition/delete";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<DeleteAttributeDto> request = new HttpEntity<>(params, headers);
//
//
//            ResponseEntity<Result<Pair>> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    request,
//                    new ParameterizedTypeReference<Result<Pair>>() {}
//            );
//
//            return response.getBody();
//        } catch (RestClientException e) {
//            return Result.error("删除失败：" + e.getMessage());
//        }
//    }
//
//    public Result<Pair> list(ListAttributeDto params, short pageSize, short curPage) {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            // 构建带路径参数的URL
//            String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                    "publicservices/rdm/basic/api/EXADefinition/query/{pageSize}/{curPage}";
//
//            // 设置请求头
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            // 将DTO对象作为请求体（API要求将查询条件放在body中）
//            HttpEntity<ListAttributeDto> requestEntity = new HttpEntity<>(params, headers);
//
//            // 发送请求并指定路径参数
//            ResponseEntity<Result<Pair>> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.GET,
//                    requestEntity,
//                    new ParameterizedTypeReference<Result<Pair>>() {},
//                    pageSize,  // 替换路径中的{pageSize}
//                    curPage    // 替换路径中的{curPage}
//            );
//
//            return response.getBody();
//        } catch (RestClientException e) {
//            return Result.error("查询失败：" + e.getMessage());
//        }
//    }
//}