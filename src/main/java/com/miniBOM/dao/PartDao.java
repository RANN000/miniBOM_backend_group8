package com.miniBOM.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.minibom.delegator.PartDelegator;
import com.huawei.innovation.rdm.minibom.dto.entity.PartViewDTO;
import com.miniBOM.pojo.Bom.BOMDelete.BOMDeleteDTO;
import com.miniBOM.pojo.Part.PartCategoryAttr.PartCategoryAttrReqVO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateReqDTO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateVO;
import com.miniBOM.pojo.Part.PartGet.PartGetVO;
import com.miniBOM.pojo.Part.PartHistory.PartHistoryResDTO;
import com.miniBOM.pojo.Part.PartSearch.PartSearchReqVO;
import com.miniBOM.pojo.Part.PartSearch.PartSearchCondition;
import com.miniBOM.pojo.Part.PartSearch.PartSearchDTO;
import com.miniBOM.pojo.Part.PartUpdate.PartUpdateReqDTO;
import com.miniBOM.pojo.Result;

import com.miniBOM.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class PartDao {
    /*
        part创建
     */

    @Autowired
    PartDelegator partDelegator;

    public String applicationId="4fc7a89107bf434faa3292b41c635750";

    public String token=getToken();

    @Scheduled(fixedRate = 6 * 60 * 60 * 1000)
//    @Scheduled(fixedRate = 6 )
    public void updateField() {
        token = getToken();
        System.out.println("token已更新为: " +token);
    }

    public String getToken(){
        Map<String, Object> paramMap = new HashMap<>();
        Map<String,Object> auth = new HashMap<>();
        Map<String,Object> identity = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("password");
        identity.put("methods",list);
        Map<String,Object> password = new HashMap<>();


        Map<String,Object> user = new HashMap<>();
        Map<String,Object> domain = new HashMap<>();
        domain.put("name","CSDN-jiankeningyao");

        user.put("domain",domain);
        user.put("name","IAM2");
        user.put("password","group888");

        password.put("user",user);
        identity.put("password",password);
        auth.put("identity",identity);
        paramMap.put("auth",auth);

        //发送
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 全局忽略null
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(paramMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        log.info("requestEntity:"+requestEntity);


        ResponseEntity<Result> response =restTemplate.exchange("https://iam.myhuaweicloud.com/v3/auth/tokens", HttpMethod.POST,
                requestEntity,
                Result.class);
        HttpHeaders responseHeaders = response.getHeaders();
        String token = responseHeaders.getFirst("X-Subject-Token");
        System.out.println("Token: " + token);
        return token;


    }


    public PartCreateVO add(PartCreateReqDTO partCreateDTO) throws JsonProcessingException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", applicationId);
        paramMap.put("params", partCreateDTO);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 全局忽略null
        String requestBody = objectMapper.writeValueAsString(paramMap);
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("x-auth-token",token);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        log.info(requestBody);
        log.info(requestEntity.toString());

        Result<List<Map<String, Object>>> result=restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/create", requestEntity, Result.class);

        PartCreateVO partCreateVO=new PartCreateVO();
        if (result != null) {
            partCreateVO.setId(result.data.get(0).get("id").toString());
        }else{
            throw new RuntimeException("Create part failed");
        }
        return partCreateVO;
    }

    /*
        part分类管理
        根据part名称或part编码查询part内容
        必填参数：版本唯一编码
     */
    public List<PartSearchReqVO> find(PartSearchDTO partSearchDTO) throws JsonProcessingException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", applicationId);
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> submap = new HashMap<>();
        submap.put("joiner","or");
        List<PartSearchCondition> conditions = new ArrayList<>();

        //必定加入的条件，当前用户id
        //TODO threadlocal 用户id转化
//        Map<String, Object> claims = ThreadLocalUtil.get();
//        String userId=claims.get("id").toString();
//        String userId="1";
//        if(userId!=null&&!userId.isEmpty()){
//            PartSearchCondition partSearchCondition = new PartSearchCondition();
//            partSearchCondition.setConditionName("userId");
//            partSearchCondition.setOperator("=");
//            List<String> searchValues = new ArrayList<>();
//            searchValues.add(userId);
//            partSearchCondition.setConditionValues(searchValues);
//            conditions.add(partSearchCondition);
//        }

//        if(partSearchDTO.getCode()!=null&& !partSearchDTO.getCode().isEmpty()){
//            PartSearchCondition partSearchCondition = new PartSearchCondition();
//            partSearchCondition.setConditionName("id");
//            partSearchCondition.setOperator("=");
//            List<String> searchValues = new ArrayList<>();
//            searchValues.add(partSearchDTO.getCode());
//            partSearchCondition.setConditionValues(searchValues);
//            conditions.add(partSearchCondition);
//        }

        if(partSearchDTO.getName()!=null&& !partSearchDTO.getName().isEmpty()){
            PartSearchCondition partSearchCondition = new PartSearchCondition();
            partSearchCondition.setConditionName("name");
            partSearchCondition.setOperator("=");
            List<String> searchValues = new ArrayList<>();
            searchValues.add(partSearchDTO.getName());
            partSearchCondition.setConditionValues(searchValues);
            conditions.add(partSearchCondition);

            if (isDigit(partSearchDTO.getName())) {
                PartSearchCondition namePartSearchCondition = new PartSearchCondition();
                namePartSearchCondition.setConditionName("id");
                namePartSearchCondition.setOperator("=");
                List<String> nameSearchValues = new ArrayList<>();
                nameSearchValues.add(partSearchDTO.getName());
                namePartSearchCondition.setConditionValues(nameSearchValues);
                conditions.add(namePartSearchCondition);
                System.out.println("namepart"+namePartSearchCondition);
            }
        }

        submap.put("conditions", conditions);
        map.put("filter", submap);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 全局忽略null
        String requestBody = objectMapper.writeValueAsString(paramMap);
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("x-auth-token", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        System.out.println("requestBody:"+requestBody);
        System.out.println(requestEntity);

        Result<List<Map<String, Object>>> result=restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/find/100000/1", requestEntity, Result.class);
        List<PartSearchReqVO> voList=new ArrayList<>();
        if (result != null) {
            for(Map<String, Object> temp:result.data){
                PartSearchReqVO vo=new PartSearchReqVO();
                if(temp.get("id")!=null&&temp.get("id")!=""){
                    vo.setId(temp.get("id").toString());
                }
                if(temp.get("name")!=null&&temp.get("name")!=""){
                    vo.setName(temp.get("name").toString());
                }
                if(temp.get("userId")!=null&&temp.get("userId")!=""){
                    vo.setUserId(temp.get("userId").toString());
                }

                if(temp.get("version")!=null&&temp.get("version")!=""){
                    vo.setVersion(temp.get("version").toString());
                }

                if(temp.get("defaultUnit")!=null&&temp.get("defaultUnit")!=""){
                    vo.setDefaultUnit(temp.get("defaultUnit").toString());
                }

                if(temp.get("iteration")!=null&&temp.get("iteration")!=""){
                    vo.setIteration(temp.get("iteration").toString());
                }

                if(temp.get("extAttrs")!=null){
                    List<Map<String,Object>> extAttrs=(List<Map<String,Object>>)temp.get("extAttrs");
                    vo.setExtAttrs(extAttrs);
                }
                if(temp.get("clsAttrs")!=null){
                    List<Map<String,Map<String,String>>> clsAttrs=(List<Map<String,Map<String,String>>>)temp.get("clsAttrs");
                    vo.setClsAttrs(clsAttrs);
                }

                voList.add(vo);
            }
        }else{
            throw new RuntimeException("Don't Search Any Match");
        }

        return voList;
    }

    public boolean isDigit(String s){
        for(int i=0;i<s.length();i++){
            if(!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }


    /*
        查询part所有历史版本对象
     */
    public List<PartHistoryResDTO> listAllVersion(String masterId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", applicationId);
        Map<String,String> map=new HashMap<>();
        map.put("masterId",masterId);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 全局忽略null
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(paramMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("x-auth-token", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        log.info(requestBody);
        log.info(requestEntity.toString());

        //TODO 分页的大小和第几页默认写死了
        Result<List<Map<String, Object>>> result=restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/getAllVersions/10/1", requestEntity, Result.class);

        List<PartHistoryResDTO> historyList=new ArrayList<>();
        if(result!=null&&result.data!=null){

            for(Map<String,Object> temp:result.data){
                PartHistoryResDTO partHistoryResDTO=new PartHistoryResDTO();
                if(temp.get("version")!=null&&temp.get("version")!=""){
                    partHistoryResDTO.setVersion((String)temp.get("version"));
                }



                if(temp.get("iteration")!=null&&temp.get("iteration")!=""){

                    partHistoryResDTO.setIteration(temp.get("iteration").toString());
                }
                if(temp.get("lastUpdateTime")!=null&&temp.get("lastUpdateTime")!=""){
                    partHistoryResDTO.setLastUpdateTime(temp.get("lastUpdateTime").toString());
                }
                System.out.println(partHistoryResDTO);
                historyList.add(partHistoryResDTO);
            }
        }else{
            throw new RuntimeException("listAllVersion Failed");
        }

        return historyList;
    }

    /*
        part修改，配合检入检出
     */
    public void update(PartUpdateReqDTO partUpdateReqDTO){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", applicationId);
        paramMap.put("params", partUpdateReqDTO);
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 全局忽略null
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(paramMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("x-auth-token", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        log.info(requestBody);
        log.info(requestEntity.toString());


        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/update", requestEntity, Result.class);
    }
    /*
    检出操作
    part修改必须先检出
    masterId必填
 */
    public void checkOut(String masterId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", applicationId);
        Map<String,String> map=new HashMap<>();
        map.put("masterId",masterId);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 全局忽略null
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(paramMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("x-auth-token", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);


        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/checkout", requestEntity, Result.class);
    }

    /*
        检入操作
        part修改完成后检入
        masterId必填
     */

    public void checkIn(String masterId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", applicationId);
        Map<String,String> map=new HashMap<>();
        map.put("masterId",masterId);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 全局忽略null
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(paramMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("x-auth-token", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);



        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/checkin", requestEntity, Result.class);
    }
    /*
        删除实例
     */
    public void delete(String id){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", applicationId);
        Map<String,String> map=new HashMap<>();

        String masterId = findMasterIdById(Long.parseLong(id)).toString();

        map.put("masterId",masterId);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 全局忽略null
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(paramMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("x-auth-token", token);
                HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        log.info(requestBody);
        log.info(requestEntity.toString());

        //TODO 删除失败的判断
        restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/delete", requestEntity, Result.class);
    }

    public List<PartCategoryAttrReqVO> listCategoryAttr(String categoryId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", "string");
        Map<String,String> map=new HashMap<>();
        map.put("nodeId",categoryId);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 全局忽略null
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(paramMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("x-auth-token", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        log.info(requestBody);
        log.info(requestEntity.toString());


        Result<List<Map<String,Object>>> result=restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/" +
                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/rdm/basic/api/ClassificationNode/getAttributesInfos/10/1", requestEntity, Result.class);
        List<PartCategoryAttrReqVO> list= new ArrayList<>();
        if(result!=null&&result.data!=null){
            for(Map<String,Object>temp:result.data){
                PartCategoryAttrReqVO partCategoryAttrReqVO = new PartCategoryAttrReqVO();
                partCategoryAttrReqVO.setId(temp.get("id").toString());
                partCategoryAttrReqVO.setName(temp.get("name").toString());
                list.add(partCategoryAttrReqVO);
            }

        }

        return list;

    }


    //根据partId查找masterId
    public Long findMasterIdById(Long partId){
        PersistObjectIdDecryptDTO partDTO = new PersistObjectIdDecryptDTO();
        partDTO.setId(partId);
        PartViewDTO partViewDTO = partDelegator.get(partDTO);
        return partViewDTO.getMaster().getId();
    }

    public PartGetVO detail(String id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applicationId", applicationId);
        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        paramMap.put("params", map);
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 全局忽略null
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(paramMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("x-auth-token", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        log.info(requestBody);
        log.info(requestEntity.toString());


        Result<List<Map<String,Object>>> result=restTemplate.postForObject("https://dme.cn-north-4.huaweicloud.com/rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/get", requestEntity, Result.class);
        System.out.println(result);
        Map<String,Object> resultMap =result.data.get(0);
        System.out.println(resultMap);
        PartGetVO partGetVO = new PartGetVO();

        if (resultMap.get("id")!=null) {
            partGetVO.setCode(resultMap.get("id").toString());
        }
        if (resultMap.get("name")!=null) {
            partGetVO.setName(resultMap.get("name").toString());
        }
        if (resultMap.get("defaultUnit")!=null) {
            partGetVO.setDefaultUnit(resultMap.get("defaultUnit").toString());
        }
        String version = resultMap.get("version").toString();
        String iteration=resultMap.get("iteration").toString();
        partGetVO.setVersion(version+"."+iteration);
        List<Map<String,String>> extList=(List<Map<String,String>>)resultMap.get("extAttrs");

        if (extList!=null&&extList.size()>0) {
            for(Map<String,String> extMap:extList){
                if(extMap.get("Classcification")!=null){
                    partGetVO.setCategory(extMap.get("Category"));
                    break;
                }
            }
        }

        return  partGetVO;

    }
}
