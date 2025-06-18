package com.miniBOM.dao;

import com.miniBOM.pojo.Part;
import com.miniBOM.pojo.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class PartDao {
    /*
        part创建
     */
    public void add(Part part){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", part);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/create", paramMap, Result.class);
    }

    /*
        part分类管理
        根据part名称或part编码查询part内容
        必填参数：版本唯一编码
     */
    public void find(Part part){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", part);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/get", paramMap, Result.class);
    }

    /*
        part历史版本对象查询
        pageSizePath 页大小
        urPagePath 第几页 从1开始

     */
    public void listAllVersion(Part part){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", part);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/getAllVersions/{pageSize}/{curPage}", paramMap, Result.class);
    }

    /*
        part修改，配合检入检出
     */
    public void update(Part part){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", part);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/update", paramMap, Result.class);
    }
    /*
    检出操作
    part修改必须先检出
    masterId必填
 */
    public void checkOut(Part part){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", part);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/checkout", paramMap, Result.class);
    }

    /*
        检入操作
        part修改完成后检入
        masterId必填
     */

    public void checkIn(Part part){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", part);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/checkin", paramMap, Result.class);
    }
    /*
        删除实例
     */
    public void delete(Part part){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        paramMap.put("params", part);
        RestTemplate restTemplate = new RestTemplate();
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


}
