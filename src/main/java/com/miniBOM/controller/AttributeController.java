package com.miniBOM.controller;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdsModifierDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionCreateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionUpdateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;
import com.miniBOM.pojo.AttributeDto.UpdateAttributeDto;
import com.miniBOM.pojo.AttributeVo.ListAttributeVo;
import com.miniBOM.pojo.AttributeVo.OneAttributeVo;
import com.miniBOM.pojo.Result;
import com.miniBOM.service.AttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
        String type = createRequest.getType();
        if(Objects.equals(type, "STRING")){
            createRequest.setConstraint("{\"associationType\":\"STRONG\",\"caseMode\":\"DEFAULT\"," +
                    "\"compose\":false,\"encryption\":false,\"graphIndex\":false,\"index\":false," +
                    "\"legalValueType\":\"\",\"length\":256,\"multiValue\":false,\"notnull\":false," +
                    "\"optionalValue\":\"LEGAL_VALUE_TYPE\",\"precision\":0,\"secretLevel\":\"internal\"," +
                    "\"stockInDB\":true,\"variable\":true}");
        }
        if(Objects.equals(type, "INTEGER")) {
            createRequest.setConstraint("{\"associationType\":\"STRONG\",\"caseMode\":\"DEFAULT\"," +
                    "\"compose\":false,\"encryption\":false,\"graphIndex\":false,\"index\":false," +
                    "\"legalValueType\":\"\",\"length\":0,\"multiValue\":false,\"notnull\":false," +
                    "\"optionalValue\":\"LEGAL_VALUE_TYPE\",\"precision\":0,\"range\":\"\",\"secretLevel\"" +
                    ":\"internal\",\"stockInDB\":true,\"variable\":true}");
        }
        // 调用服务层创建属性
        EXADefinitionViewDTO createdAttribute = attributeService.add(createRequest);

        // 转换为前端视图对象
        OneAttributeVo resultView = new OneAttributeVo();
        BeanUtils.copyProperties(createdAttribute, resultView);

        return Result.success(resultView);
    }

    /**
     * 根据id查询属性
     * @param id 根据id
     * @return 查询成功后的属性视图对象
     */
    @GetMapping("/get/{id}")
    public Result<OneAttributeVo> getById(@PathVariable Long id) {
        List<EXADefinitionViewDTO> list = attributeService.getById(id);
        OneAttributeVo attributeVO = new OneAttributeVo();

        // 增加空值校验
        if (list == null || list.isEmpty()) {
            return Result.error("未找到对应ID的属性");
        }

        BeanUtils.copyProperties(list.get(0), attributeVO);
        return Result.success(attributeVO);
    }


    /**
     * 分页查询属性定义列表
     *
     * @param searchKey 搜索关键词，匹配属性名称或编码
     * @param pageSize      每页记录数
     * @param curPage   当前页码
     * @return 分页结果，包含属性列表和总记录数
     */
    @GetMapping({"/list/{searchKey}/{pageSize}/{curPage}","/list/{pageSize}/{curPage}"})
    public Result<ListAttributeVo> list(
            @PathVariable(required = false) String searchKey,
            @PathVariable Integer pageSize,
            @PathVariable Integer curPage
    ) {
        // 1. 查询属性列表
        List<EXADefinitionViewDTO> attributeDefinitions = attributeService
                .list(searchKey, pageSize, curPage);

        // 2. 转换DTO为展示VO
        List<OneAttributeVo> attributeViews = getOneAttributeVos(attributeDefinitions);

        // 3. 查询总记录数
        long totalRecords = attributeService.count(searchKey);

        // 4. 构建分页结果
        ListAttributeVo pagedResult = ListAttributeVo.builder()
                .list(attributeViews)
                .number((int) totalRecords)
                .build();

        return Result.success(pagedResult);

    }

    /**
     * 工具函数
     * 转换DTO为VO
     * @return VO列表
     */
    private static List<OneAttributeVo> getOneAttributeVos(List<EXADefinitionViewDTO> attributeDefinitions) {
        List<OneAttributeVo> attributeViews = new ArrayList<>(attributeDefinitions.size());
        for (EXADefinitionViewDTO definition : attributeDefinitions) {
            OneAttributeVo view = new OneAttributeVo();
            view.setId(definition.getId());
            view.setName(definition.getName());
            view.setNameEn(definition.getNameEn());
            view.setDescription(definition.getDescription());
            view.setDescriptionEn(definition.getDescriptionEn());
            view.setType(definition.getType());
            view.setDisableFlag(definition.getDisableFlag());
            attributeViews.add(view);
        }
        return attributeViews;
    }

    /**
     * 更新属性定义
     *
     * @param request 更新请求DTO，包含属性定义的更新数据
     * @return 更新后的属性信息
     */
    @PutMapping("/update")
    public Result<UpdateAttributeDto> updateAttribute(@RequestBody UpdateAttributeDto request) {
        // 转换请求DTO为服务层使用的更新DTO
        EXADefinitionUpdateDTO serviceRequest = new EXADefinitionUpdateDTO();
        BeanUtils.copyProperties(request, serviceRequest);

        // 调用服务层执行更新操作
        EXADefinitionViewDTO updatedRecord = attributeService.update(serviceRequest);

        // 将更新后的实体数据回写到响应DTO
        BeanUtils.copyProperties(updatedRecord, request);

        return Result.success(request);
    }

    /**
     * 批量删除属性定义
     *
     * @param deleteRequest 包含待删除记录ID列表的请求对象
     * @return 操作结果
     */
    @DeleteMapping("/delete")
    public Result deleteAttributes(@RequestBody PersistObjectIdsModifierDTO deleteRequest) {

        // 调用服务层执行批量删除操作
        attributeService.delete(deleteRequest);

        // 返回无数据的成功响应
        return Result.success();
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