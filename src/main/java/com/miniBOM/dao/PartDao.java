package com.miniBOM.dao;

import com.miniBOM.pojo.Part.Part;
import com.miniBOM.pojo.Part.PartCategoryAttr.PartCategoryAttrReqVO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateReqDTO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateVO;
import com.miniBOM.pojo.Part.PartHistory.PartHistoryResDTO;
import com.miniBOM.pojo.Part.PartSearch.PartSearchReqVO;
import com.miniBOM.pojo.Part.PartSearch.PartSearchCondition;
import com.miniBOM.pojo.Part.PartSearch.PartSearchDTO;
import com.miniBOM.pojo.Part.PartUpdate.PartUpdateReqDTO;
import com.miniBOM.pojo.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
//TODO appicaitionId
public class PartDao {
    /*
        part创建
     */
    public PartCreateVO add(PartCreateReqDTO partCreateDTO){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", partCreateDTO);
        RestTemplate restTemplate = new RestTemplate();
        //TODO Part对华为云返回对象建模
        Result<PartCreateVO> result=restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/create", paramMap, Result.class);
        return result.data;
    }

    /*
        part分类管理
        根据part名称或part编码查询part内容
        必填参数：版本唯一编码
     */
    public List<PartSearchReqVO> find(PartSearchDTO partSearchDTO){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> submap = new HashMap<>();
        submap.put("joiner","and");
        List<PartSearchCondition> conditions = new ArrayList<>();

        if(partSearchDTO.getCode()!=null&& !partSearchDTO.getCode().isEmpty()){
            PartSearchCondition partSearchCondition = new PartSearchCondition();
            partSearchCondition.setConditionName("id");
            List<String> searchValues = new ArrayList<>();
            searchValues.add(partSearchDTO.getCode());
            partSearchCondition.setConditionValue(searchValues);
            conditions.add(partSearchCondition);
        }

        if(partSearchDTO.getName()!=null&& !partSearchDTO.getName().isEmpty()){
            PartSearchCondition partSearchCondition = new PartSearchCondition();
            partSearchCondition.setConditionName("name");
            List<String> searchValues = new ArrayList<>();
            searchValues.add(partSearchDTO.getName());
            partSearchCondition.setConditionValue(searchValues);
            conditions.add(partSearchCondition);
        }

        submap.put("conditions",conditions);
        map.put("filter", submap);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();
        Result<List<PartSearchReqVO>> result=restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/find/10/1", paramMap, Result.class);
        return result.data;
    }

    /*
        查询part所有历史版本对象
        pageSizePath 页大小
        urPagePath 第几页 从1开始

     */
    public List<PartHistoryResDTO> listAllVersion(String masterId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        Map<String,String> map=new HashMap<>();
        map.put("masterId",masterId);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();
        //TODO 分页的大小和第几页默认写死了
        Result<List<PartHistoryResDTO>> result=restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/getAllVersions/10/1", paramMap, Result.class);
        return result.data;
    }

    /*
        part修改，配合检入检出
     */
    public void update(PartUpdateReqDTO partUpdateReqDTO){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", partUpdateReqDTO);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/update", paramMap, Result.class);
    }
    /*
    检出操作
    part修改必须先检出
    masterId必填
 */
    public void checkOut(String masterId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        Map<String,String> map=new HashMap<>();
        map.put("masterId",masterId);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/checkout", paramMap, Result.class);
    }

    /*
        检入操作
        part修改完成后检入
        masterId必填
     */

    public void checkIn(String masterId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        Map<String,String> map=new HashMap<>();
        map.put("masterId",masterId);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/checkin", paramMap, Result.class);
    }
    /*
        删除实例
     */
    public void delete(String id){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();
        //TODO 删除失败的判断
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/delete", paramMap, Result.class);
    }

    /*
        分页查询，暂时不知道条件查询参数
        pageSizePath 页大小
        urPagePath 第几页 从1开始
        identifier 应用标识
        modelName 模型类型

     */
    public List<Part> list(String searchKey){
        int pageSize = 10;
        int curPage = 1;
        RestTemplate restTemplate=new RestTemplate();
        Result<List<Part>> result = restTemplate.getForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/find/{pageSize}/{curPage}", Result.class,pageSize,curPage);
        if (result != null) {
            return result.data;
        }
        return null;
    }


    public List<PartCategoryAttrReqVO> listCategoryAttr(String categoryId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        Map<String,String> map=new HashMap<>();
        map.put("nodeId",categoryId);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();
        //TODO 分页的大小和第几页默认写死了
        Result<List<PartCategoryAttrReqVO>> result=restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/rdm/basic/api/ClassificationNode/getAttributesInfos/10/1", paramMap, Result.class);
        return result.data;

    }
}
