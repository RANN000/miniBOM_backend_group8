package com.miniBOM.dao;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdsModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.QueryChildListDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.xdm.delegator.EXADefinitionDelegator;
import com.huawei.innovation.rdm.xdm.dto.entity.*;

import com.miniBOM.pojo.AttributeDto.ListAttributeDto;
import com.miniBOM.pojo.AttributeVo.ListAttributeVo;
import com.miniBOM.pojo.AttributeVo.OneAttributeVo;
import com.miniBOM.pojo.ClassificationVo.ListClassificationVo;
import com.miniBOM.pojo.ClassificationVo.OneClassificationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 属性数据访问对象(DAO)
 * 负责属性(Attribute)相关的数据持久化操作，包括查询、添加、更新和删除等功能
 * 依赖 EXADefinitionDelegator 实现具体的数据访问逻辑
 */
@Component
public class AttributeDao {

    @Autowired
    private EXADefinitionDelegator exaDefinitionDelegator;

    /**
     * 根据属性ID查询属性信息
     *
     * @param id 属性唯一标识符
     * @return 包含属性信息的视图对象列表，可能为空列表但不会为null
     */
    public List<EXADefinitionViewDTO> getById(Long id) {
        // 构建查询条件
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("id", ConditionType.EQUAL, id);

        // 构建分页参数（仅查询1条记录）
        RDMPageVO rdmPageVO = new RDMPageVO();
        rdmPageVO.setCurPage(1);
        rdmPageVO.setPageSize(1);

        return exaDefinitionDelegator.find(queryRequestVo, rdmPageVO);
    }

    /**
     * 统计符合搜索条件的属性数量
     * 工具函数
     * @param searchKey 搜索关键词（基于属性名称模糊查询），可为null或空字符串
     * @return 符合条件的属性记录总数
     */
    public long count(String searchKey) {
        QueryRequestVo queryRequestVo = new QueryRequestVo();

        // 当搜索关键词有效时添加模糊查询条件
        if (StringUtils.hasText(searchKey)) {
            queryRequestVo.addCondition("name", ConditionType.LIKE, searchKey);
        }

        return exaDefinitionDelegator.count(queryRequestVo);
    }

    /**
     * 添加新属性
     *
     * @param exaDefinitionCreateDTO 包含新属性信息的创建DTO
     * @return 新增后的属性视图对象，包含完整属性信息
     */
    public EXADefinitionViewDTO add(EXADefinitionCreateDTO exaDefinitionCreateDTO) {
        if(exaDefinitionCreateDTO.getName()==null){
            exaDefinitionCreateDTO.setName("未定义");
        }
        if(exaDefinitionCreateDTO.getNameEn()==null){
            exaDefinitionCreateDTO.setNameEn("undefined");
        }
        String type = exaDefinitionCreateDTO.getType();
        if(type==null){
            exaDefinitionCreateDTO.setType("STRING");
            type = exaDefinitionCreateDTO.getType();
        }
        if(Objects.equals(type, "STRING")){
            exaDefinitionCreateDTO.setConstraint("{\"associationType\":\"STRONG\",\"caseMode\":\"DEFAULT\"," +
                    "\"compose\":false,\"encryption\":false,\"graphIndex\":false,\"index\":false," +
                    "\"legalValueType\":\"\",\"length\":256,\"multiValue\":false,\"notnull\":false," +
                    "\"optionalValue\":\"LEGAL_VALUE_TYPE\",\"precision\":0,\"secretLevel\":\"internal\"," +
                    "\"stockInDB\":true,\"variable\":true}");
        }
        if(Objects.equals(type, "INTEGER")) {
            exaDefinitionCreateDTO.setConstraint("{\"associationType\":\"STRONG\",\"caseMode\":\"DEFAULT\"," +
                    "\"compose\":false,\"encryption\":false,\"graphIndex\":false,\"index\":false," +
                    "\"legalValueType\":\"\",\"length\":0,\"multiValue\":false,\"notnull\":false," +
                    "\"optionalValue\":\"LEGAL_VALUE_TYPE\",\"precision\":0,\"range\":\"\",\"secretLevel\"" +
                    ":\"internal\",\"stockInDB\":true,\"variable\":true}");
        }
        return exaDefinitionDelegator.create(exaDefinitionCreateDTO);
    }

    /**
     * 更新属性信息
     *
     * @param exaDefinitionUpdateDTO 包含更新后属性信息的DTO（需包含ID）
     * @return 更新后的属性视图对象
     * @throws IllegalArgumentException 当根据ID未找到对应属性时抛出
     */
    public EXADefinitionViewDTO update(EXADefinitionUpdateDTO exaDefinitionUpdateDTO) {
        // 先查询当前属性信息
        List<EXADefinitionViewDTO> list = getById(exaDefinitionUpdateDTO.getId());

        // 校验数据存在性
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("未找到ID为[" + exaDefinitionUpdateDTO.getId() + "]的属性");
        }

        EXADefinitionViewDTO exaDefinitionViewDTO = list.get(0);

        // 保留未更新的字段（从原对象获取）
        exaDefinitionUpdateDTO.setName(exaDefinitionViewDTO.getName());
        exaDefinitionUpdateDTO.setType(exaDefinitionViewDTO.getType());
        exaDefinitionUpdateDTO.setConstraint(exaDefinitionViewDTO.getConstraint());
        exaDefinitionUpdateDTO.setCategory(exaDefinitionViewDTO.getCategory());

        return exaDefinitionDelegator.update(exaDefinitionUpdateDTO);
    }

    /**
     * 批量删除属性
     *
     * @param persistObjectIdsModifierDTO 包含待删除属性ID列表的DTO
     * @throws IllegalArgumentException 当ID列表为空时抛出
     */
    public void deleteAttributes(PersistObjectIdsModifierDTO persistObjectIdsModifierDTO) {
        // 校验ID列表有效性
        if (persistObjectIdsModifierDTO == null || persistObjectIdsModifierDTO.getIds() == null ||
                persistObjectIdsModifierDTO.getIds().isEmpty()) {
            throw new IllegalArgumentException("删除操作需要提供有效的ID列表");
        }

        exaDefinitionDelegator.batchDelete(persistObjectIdsModifierDTO);
    }

    /**
     * 分页查询属性列表
     *
     * @param listAttributeDto 请求体
     * @return 符合条件的属性视图对象列表，可能为空列表但不会为null
     * @throws IllegalArgumentException 当页码或页大小为非正数时抛出
     */
    public ListAttributeVo list(ListAttributeDto listAttributeDto) {
        QueryRequestVo queryRequest = new QueryRequestVo();
        RDMPageVO rdm = new RDMPageVO();

        // 设置分页参数
        Integer currentPage = listAttributeDto.getCurPage();
        Integer pageSize = listAttributeDto.getPageSize();

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
        if (listAttributeDto.getId() != null) {
            queryRequest.addCondition("id", ConditionType.EQUAL, listAttributeDto.getId());
        }

        if (listAttributeDto.getName() != null) {
            queryRequest.addCondition("name", ConditionType.LIKE, listAttributeDto.getName());
        }

        long totalCount = exaDefinitionDelegator.count(queryRequest);
        List<EXADefinitionViewDTO> resultList = exaDefinitionDelegator.find(queryRequest, rdm);

        // 转换结果并封装分页信息
        List<OneAttributeVo> attributeVos = getOneAttributeVos(resultList);

        return ListAttributeVo.builder()
                .number((int) totalCount)
                .list(attributeVos)
                .build();

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
     * 删除属性
     *
     * @param deleteId 待删除属性ID
     */
    public void delete(Long deleteId) {
        // 检查是否存在
        QueryRequestVo queryRequest = new QueryRequestVo();
        queryRequest.addCondition("id", ConditionType.EQUAL, deleteId);

        RDMPageVO pageParams = new RDMPageVO();
        pageParams.setCurPage(1);
        pageParams.setPageSize(1);

        List<EXADefinitionViewDTO> resultList = exaDefinitionDelegator.find(queryRequest, pageParams);
        if (resultList.isEmpty()) {
            throw new RuntimeException("属性不存在，无法删除");
        }

        // 删除操作
        PersistObjectIdModifierDTO deleteRequest = new PersistObjectIdModifierDTO();
        deleteRequest.setId(deleteId);

        exaDefinitionDelegator.delete(deleteRequest);
    }
}
