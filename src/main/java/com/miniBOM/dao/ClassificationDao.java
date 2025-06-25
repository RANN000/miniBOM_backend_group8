package com.miniBOM.dao;

import com.huawei.innovation.rdm.coresdk.basic.dto.ObjectReferenceParamDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.QueryChildListDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.xdm.delegator.ClassificationNodeDelegator;
import com.huawei.innovation.rdm.xdm.dto.entity.*;
import com.miniBOM.pojo.ClassificationDto.*;
import com.miniBOM.pojo.ClassificationVo.ListClassificationVo;
import com.miniBOM.pojo.ClassificationVo.OneClassificationVo;

import com.miniBOM.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassificationDao {

    @Autowired
    private ClassificationNodeDelegator delegator;


    /**
     * 统计符合搜索条件的分类节点数量
     *
     * @param searchKey 搜索关键词（基于分类名称模糊查询），可为null或空字符串
     * @return 符合条件的分类节点总数
     */
    public long count(String searchKey) {
        QueryRequestVo queryRequest = new QueryRequestVo();

        // 添加模糊搜索条件（如果关键词有效）
        if (StringUtils.hasText(searchKey)) {
            queryRequest.addCondition("name", ConditionType.LIKE, searchKey);
        }

        return delegator.count(queryRequest);
    }

    /**
     * 根据分类ID查询分类信息
     *
     * @param id 分类唯一标识符
     * @return 包含分类信息的视图对象列表，可能为空列表但不会为null
     */
    public List<ClassificationNodeViewDTO> getById(Long id) {
        // 构建ID精确匹配查询条件
        QueryRequestVo queryRequest = new QueryRequestVo();
        queryRequest.addCondition("id", ConditionType.EQUAL, id);

        // 配置分页参数（仅查询1条记录）
        RDMPageVO pageParams = new RDMPageVO();
        pageParams.setCurPage(1);
        pageParams.setPageSize(1);

        return delegator.find(queryRequest, pageParams);
    }

    /**
     * 创建新的分类节点
     *
     * @param classificationDto 创建分类的请求DTO
     * @return 创建成功后的分类视图对象
     */
    public OneClassificationVo add(CreateClassificationDto classificationDto) {
        // 构建分类创建请求DTO
        ClassificationNodeCreateDTO createRequest = new ClassificationNodeCreateDTO();
        createRequest.setId(classificationDto.getCode());
        createRequest.setName(classificationDto.getName());
        createRequest.setNameEn(classificationDto.getNameEn());
        createRequest.setDescription(classificationDto.getDescription());
        createRequest.setDescriptionEn(classificationDto.getDescriptionEn());
        createRequest.setBusinessCode(classificationDto.getBusinessCode());
        createRequest.setDisableFlag(!classificationDto.getStatus());
        createRequest.setInstantiable(classificationDto.getIsInstance());

        // 设置父节点信息（如果存在）
        if (classificationDto.getParentId() != null) {
            ObjectReferenceParamDTO parentNode = new ObjectReferenceParamDTO();
            parentNode.setId(classificationDto.getParentId());
            createRequest.setParentNode(parentNode);
        }

        // 执行创建并转换结果
        ClassificationNodeViewDTO result = delegator.create(createRequest);
        OneClassificationVo resultVo = new OneClassificationVo();
        resultVo.setCode(result.getId());

        return resultVo;
    }

    /**
     * 更新现有分类节点信息
     *
     * @param classificationDto 更新分类的请求DTO
     * @return 更新后的分类视图对象
     * @throws RuntimeException 当分类不存在时抛出异常
     */
    public OneClassificationVo update(UpdateClassificationDto classificationDto) {
        // 1. 查询待更新的分类信息
        QueryRequestVo queryRequest = new QueryRequestVo();
        queryRequest.addCondition("id", ConditionType.EQUAL, classificationDto.getId());

        RDMPageVO pageParams = new RDMPageVO();
        pageParams.setCurPage(1);
        pageParams.setPageSize(1);

        List<ClassificationNodeViewDTO> resultList = delegator.find(queryRequest, pageParams);
        if (resultList.isEmpty()) {
            throw new RuntimeException("分类信息不存在");
        }

        // 2. 构建更新请求DTO（只更新有值的字段）
        ClassificationNodeUpdateDTO updateRequest = new ClassificationNodeUpdateDTO();
        updateRequest.setId(classificationDto.getId());

        if (classificationDto.getName()!=null) {
            updateRequest.setName(classificationDto.getName());
        }
        if (classificationDto.getNameEn()!=null) {
            updateRequest.setNameEn(classificationDto.getNameEn());
        }
        if (classificationDto.getDescription()!=null) {
            updateRequest.setDescription(classificationDto.getDescription());
        }
        if (classificationDto.getDescriptionEn()!=null) {
            updateRequest.setDescriptionEn(classificationDto.getDescriptionEn());
        }
        if (classificationDto.getStatus()) {
            updateRequest.setDisableFlag(!classificationDto.getStatus());
        }
        if (classificationDto.getIsInstance()) {
            updateRequest.setInstantiable(classificationDto.getIsInstance());
        }



        // 3. 执行更新并转换结果
        ClassificationNodeViewDTO updatedCategory = delegator.update(updateRequest);

        OneClassificationVo resultVo = new OneClassificationVo();
        resultVo.setCode(updatedCategory.getId());
        resultVo.setName(updatedCategory.getName());
        resultVo.setNameEn(updatedCategory.getNameEn());
        resultVo.setDescription(updatedCategory.getDescription());
        resultVo.setDescriptionEn(updatedCategory.getDescriptionEn());


        return resultVo;
    }

    /**
     * 删除指定ID的分类节点（仅当该分类没有子分类时）
     *
     * @param deleteId 要删除的分类ID
     * @throws RuntimeException 当分类不存在或存在子分类时抛出异常
     */
    public void delete(Long deleteId) {
        // 1. 检查分类是否存在
        QueryRequestVo queryRequest = new QueryRequestVo();
        queryRequest.addCondition("id", ConditionType.EQUAL, deleteId);

        RDMPageVO pageParams = new RDMPageVO();
        pageParams.setCurPage(1);
        pageParams.setPageSize(1);

        List<ClassificationNodeViewDTO> resultList = delegator.find(queryRequest, pageParams);
        if (resultList.isEmpty()) {
            throw new RuntimeException("分类不存在，无法删除");
        }

        // 2. 检查是否存在子分类
        QueryChildListDTO childQuery = new QueryChildListDTO();
        childQuery.setParentId(deleteId);

        List<ClassificationNodeQueryViewDTO> children = delegator.getChildList(childQuery, new RDMPageVO());

        if (children != null && !children.isEmpty()) {
            throw new RuntimeException("该分类下存在子分类，不允许删除");
        }

        // 3. 执行删除操作
        PersistObjectIdModifierDTO deleteRequest = new PersistObjectIdModifierDTO();
        deleteRequest.setId(deleteId);

        delegator.delete(deleteRequest);
    }

    /**
     * 分页查询分类节点列表
     *
     * @param classificationDto 分页查询条件
     * @return 分页封装的分类视图对象列表
     */
    public ListClassificationVo list(ListClassificationDto classificationDto) {
        QueryRequestVo queryRequest = new QueryRequestVo();
        RDMPageVO rdm = new RDMPageVO();

        // 设置分页参数
        Integer currentPage = classificationDto.getCurPage();
        Integer pageSize = classificationDto.getPageSize();

        if (currentPage != null) {
            rdm.setCurPage(currentPage);
        } else {
            rdm.setCurPage(1);
        }

        if (pageSize != null) {
            rdm.setPageSize(pageSize);
        } else {
            rdm.setPageSize(10);
        }

        // 添加查询条件
        if (classificationDto.getCode() != null) {
            queryRequest.addCondition("id", ConditionType.EQUAL, classificationDto.getCode());
        }

        if (classificationDto.getName() != null) {
            queryRequest.addCondition("name", ConditionType.LIKE, classificationDto.getName());
        }

        // 获取总数并执行分页查询
        long totalCount = delegator.count(queryRequest);
        List<ClassificationNodeViewDTO> resultList = delegator.find(queryRequest, rdm);

        // 转换结果并封装分页信息
        List<OneClassificationVo> categoryVOs = convertToCategoryVO(resultList);

        return ListClassificationVo.builder()
                .number((int) totalCount)
                .list(categoryVOs)
                .build();
    }

    /**
     * DTO转换为VO（提取分类核心属性）
     */
    private List<OneClassificationVo> convertToCategoryVO(List<ClassificationNodeViewDTO> dtoList) {
        List<OneClassificationVo> voList = new ArrayList<>();

        if (dtoList == null) {
            return voList;
        }

        for (ClassificationNodeViewDTO dto : dtoList) {
            OneClassificationVo vo = new OneClassificationVo();

            // 基础属性映射
            vo.setCode(dto.getId());
            vo.setName(dto.getName());
            vo.setNameEn(dto.getNameEn());
            vo.setDescription(dto.getDescription());
            vo.setDescriptionEn(dto.getDescriptionEn());

            // 状态映射（根据disableFlag取反）
            Boolean disableFlag = dto.getDisableFlag();
            vo.setStatus(disableFlag == null || !disableFlag);

            // 父节点ID映射
            if(dto.getParentNode() != null){
                vo.setParentId(dto.getParentNode().getId());
            }
            else{
                vo.setParentId(null);
            }

            voList.add(vo);
        }

        return voList;
    }



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

//
//    public Result<OneClassificationVo> add(CreateClassificationDto params) {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                    "publicservices/rdm/basic/api/ClassificationNode/create";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<CreateClassificationDto> request = new HttpEntity<>(params, headers);
//
//
//            ResponseEntity<Result<OneClassificationVo>> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    request,
//                    new ParameterizedTypeReference<Result<OneClassificationVo>>() {}
//            );
//
//            return response.getBody();
//        } catch (RestClientException e) {
//            return Result.error("添加失败：" + e.getMessage());
//        }
//    }
//
//    public Result<OneClassificationVo> update(UpdateClassificationDto params) {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//
//            String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                    "publicservices/rdm/basic/api/ClassificationNode/update";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<UpdateClassificationDto> request = new HttpEntity<>(params, headers);
//
//
//            ResponseEntity<Result<OneClassificationVo>> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    request,
//                    new ParameterizedTypeReference<Result<OneClassificationVo>>() {}
//            );
//
//            return response.getBody();
//        } catch (RestClientException e) {
//            return Result.error("更新失败：" + e.getMessage());
//        }
//    }
//
//    public Result delete(DeleteClassificationDto params) {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                    "publicservices/rdm/basic/api/ClassificationNode/delete";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<DeleteClassificationDto> request = new HttpEntity<>(params, headers);
//
//
//            ResponseEntity<Result> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    request,
//                    new ParameterizedTypeReference<Result>() {}
//            );
//
//            return response.getBody();
//        } catch (RestClientException e) {
//            return Result.error("删除失败：" + e.getMessage());
//        }
//    }
//
//    public Result<ListClassificationVo> list(ListClassificationDto dto) throws JsonProcessingException {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            // 构建带路径参数的URL
//            String url = "https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/" +
//                    "publicservices/rdm/common/api/ClassificationNode/list/{pageSize}/{curPage}";
//
//            // 设置请求头
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            QueryRequestVo queryRequestVo = new QueryRequestVo();
//
//            // 当搜索关键词有效时添加模糊查询条件
//            if (StringUtils.hasText(searchKey)) {
//                queryRequestVo.addCondition(AttributeConstant.NAME, ConditionType.LIKE, searchKey);
//            }
//            // 发送请求并指定路径参数
//            ResponseEntity<Result<ListClassificationVo>> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<Result<ListClassificationVo>>() {},
//                    pageSize,  // 替换路径中的{pageSize}
//                    curPage    // 替换路径中的{curPage}
//            );
//
//            return response.getBody();
//        } catch (RestClientException e) {
//            return Result.error("查询失败：" + e.getMessage());
//        }
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("applicationId", "applicationId");
//        Map<String,Object> map = new HashMap<>();
//        Map<String,Object> submap = new HashMap<>();
//        submap.put("joiner","and");
//        List<PartSearchCondition> conditions = new ArrayList<>();
//
//        if(dto.getName()!=null&& !dto.getName().isEmpty()){
//            PartSearchCondition partSearchCondition = new PartSearchCondition();
//            partSearchCondition.setConditionName("name");
//            List<String> searchValues = new ArrayList<>();
//            searchValues.add(dto.getName());
//            partSearchCondition.setConditionValue(searchValues);
//            conditions.add(partSearchCondition);
//        }
//
//        submap.put("conditions",conditions);
//        map.put("filter", submap);
//        paramMap.put("params", map);
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 全局忽略null
//        String requestBody = objectMapper.writeValueAsString(paramMap);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        headers.add("x-auth-token", "MIIN2gYJKoZIhvcNAQcCoIINyzCCDccCAQExDTALBglghkgBZQMEAgEwggvsBgkqhkiG9w0BBwGgggvdBIIL2XsidG9rZW4iOnsiZXhwaXJlc19hdCI6IjIwMjUtMDYtMjNUMDU6NTI6MTUuODc1MDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwiZG9tYWluIjp7Im5hbWUiOiJDU0ROLWppYW5rZW5pbmd5YW8iLCJpZCI6IjBkOGUzNGEzNzE0NzRiMmRiYmEzYzRkN2U0MmZlZmI2In0sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6IjAifSx7Im5hbWUiOiJzZWN1X2FkbWluIiwiaWQiOiIwIn0seyJuYW1lIjoidGVfYWdlbmN5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3Nic19yZXBfYWNjZWxlcmF0aW9uIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2Rpc2tBY2MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kc3NfbW9udGgiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9vYnNfZGVlcF9hcmNoaXZlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1zb3V0aC00YyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2RlY19tb250aF91c2VyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2JyX3NlbGxvdXQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3Nfb2xkX3Jlb3VyY2UiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfUm95YWx0eSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3dlbGlua2JyaWRnZV9lbmRwb2ludF9idXkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jYnJfZmlsZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rtcy1yb2NrZXRtcTUtYmFzaWMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kbXMta2Fma2EzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfb2JzX2RlY19tb250aCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NzYnNfcmVzdG9yZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Nicl92bXdhcmUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pZG1lX21ibV9mb3VuZGF0aW9uIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2M2YSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX3BjX3ZlbmRvcl9zdWJ1c2VyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbXVsdGlfYmluZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3Ntbl9jYWxsbm90aWZ5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtM2QiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3Byb2dyZXNzYmFyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2VzX3Jlc291cmNlZ3JvdXBfdGFnIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX29mZmxpbmVfYWM3IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXZzX3JldHlwZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2tvb21hcCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c19lc3NkMiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rtcy1hbXFwLWJhc2ljIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXZzX3Bvb2xfY2EiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLXNvdXRod2VzdC0yYiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2h3Y3BoIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX29mZmxpbmVfZGlza180IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaHdkZXYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zbW5fd2VsaW5rcmVkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaHZfdmVuZG9yIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfY24tbm9ydGgtNGQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfaGVjc194IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2JyX2ZpbGVzX2JhY2t1cCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19hYzciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lcHMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3Jlc3RvcmVfYWxsIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX3JvdW5kdGFibGUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfZXh0IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfcGZzX2RlZXBfYXJjaGl2ZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfYXAtc291dGhlYXN0LTFlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9ydS1tb3Njb3ctMWIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2FwLXNvdXRoZWFzdC0xZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FwcHN0YWdlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtMWYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zbW5fYXBwbGljYXRpb24iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfY29sZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3Jkc19jYSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19ncHVfZzVyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfb3BfZ2F0ZWRfbWVzc2FnZW92ZXI1ZyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19yaSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfcnUtbm9ydGh3ZXN0LTJjIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmX3BsYXRpbnVtIiwiaWQiOiIwIn1dLCJpc3N1ZWRfYXQiOiIyMDI1LTA2LTIyVDA1OjUyOjE1Ljg3NTAwMFoiLCJ1c2VyIjp7ImRvbWFpbiI6eyJuYW1lIjoiQ1NETi1qaWFua2VuaW5neWFvIiwiaWQiOiIwZDhlMzRhMzcxNDc0YjJkYmJhM2M0ZDdlNDJmZWZiNiJ9LCJuYW1lIjoiSUFNMiIsInBhc3N3b3JkX2V4cGlyZXNfYXQiOiIiLCJpZCI6IjkxYjQzM2Q0N2FlYzQxNWZhYjkwNzc0ZWI5MTUwNTQ5In19fTGCAcEwggG9AgEBMIGXMIGJMQswCQYDVQQGEwJDTjESMBAGA1UECAwJR3VhbmdEb25nMREwDwYDVQQHDAhTaGVuWmhlbjEuMCwGA1UECgwlSHVhd2VpIFNvZnR3YXJlIFRlY2hub2xvZ2llcyBDby4sIEx0ZDEOMAwGA1UECwwFQ2xvdWQxEzARBgNVBAMMCmNhLmlhbS5wa2kCCQDcsytdEGFqEDALBglghkgBZQMEAgEwDQYJKoZIhvcNAQEBBQAEggEANH7WnJ2fLP0Jj9eND2-eIAkHFl+vmY0-QehVFduLySxt+JzZ7jzGAPOmPI-XtJiFX+1K7KDvJoc3FgdO5p-cC2ObLdFRi2jI-NMRJtXGlSp3ia+Eb-UllXIL5czCQip6DHLTJNaP9UswRh7R8SQpnCFadvHH6ww2dUVdu1ecIQy5j-JSX3bf7SP9J1IqPpGn6dZp3G4-qLSO9+yjAGTWQacNzgkBFT-zkt3r54l-swbfnGJ4SlSKRviBXEXmRMZALqvcr2GYpekh6OrxmCwL+gpUO-R+dbE+EZSTd0jUPYp85PeDzQmbpBPU0f3NZePtPWVuDBq-i0NAdPydPOm-zg==");
//        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
//
//
//        Result<List<Map<String, Object>>> result=restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
//                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/rdm/common/api/ClassificationNode/list/{pageSize}/{curPage}",
//                requestEntity, Result.class);
//        List<OneClassificationVo> voList=new ArrayList<>();
//        int number=0;
//        if (result != null) {
//            for(Map<String, Object> temp:result.data){
//                OneClassificationVo vo=new OneClassificationVo();
//                if(temp.get("id")!=null&&temp.get("id")!=""){
//                    vo.setId((Long) temp.get("id"));
//                }
//                if(temp.get("name")!=null&&temp.get("name")!=""){
//                    vo.setName(temp.get("name").toString());
//                }
//                if(temp.get("nameEn")!=null&&temp.get("nameEn")!=""){
//                    vo.setName(temp.get("nameEn").toString());
//                }
//                if(temp.get("description")!=null&&temp.get("description")!=""){
//                    vo.setDescription(temp.get("description").toString());
//                }
//                if(temp.get("descriptionEn")!=null&&temp.get("descriptionEn")!=""){
//                    vo.setDescription(temp.get("descriptionEn").toString());
//                }
//                number++;
//                voList.add(vo);
//            }
//        }else{
//            throw new RuntimeException("Don't Search Any Match");
//        }
//        ListClassificationVo vo=new ListClassificationVo(number,voList);
//        return Result.success(vo);
//    }



//    public Result addAttribute(AddClassificationNodeAttributeDto params) {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
//                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/rdm/basic/api/" +
//                "ClassificationNode/attribute/add", params, Result.class);
//    }
//
//    public Result deleteAttribute(DeleteClassificationNodeAttributeDto params) {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
//                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/rdm/basic/api/" +
//                "ClassificationNode/attribute/remove", params, Result.class);
//    }
}