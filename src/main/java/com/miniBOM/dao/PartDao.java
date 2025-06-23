package com.miniBOM.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.miniBOM.pojo.Part.PartCategoryAttr.PartCategoryAttrReqVO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateReqDTO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateVO;
import com.miniBOM.pojo.Part.PartHistory.PartHistoryResDTO;
import com.miniBOM.pojo.Part.PartSearch.PartSearchReqVO;
import com.miniBOM.pojo.Part.PartSearch.PartSearchCondition;
import com.miniBOM.pojo.Part.PartSearch.PartSearchDTO;
import com.miniBOM.pojo.Part.PartUpdate.PartUpdateReqDTO;
import com.miniBOM.pojo.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    public String applicationId="4fc7a89107bf434faa3292b41c635750";
    public String token=
            "MIIN2gYJKoZIhvcNAQcCoIINyzCCDccCAQExDTALBglghkgBZQMEAgEwggvsBgkqhkiG9w0BBwGgggvdBIIL2XsidG9rZW4iOnsiZXhwaXJlc19hdCI6IjIwMjUtMDYtMjRUMDc6Mjk6MzMuODc1MDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwiZG9tYWluIjp7Im5hbWUiOiJDU0ROLWppYW5rZW5pbmd5YW8iLCJpZCI6IjBkOGUzNGEzNzE0NzRiMmRiYmEzYzRkN2U0MmZlZmI2In0sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6IjAifSx7Im5hbWUiOiJzZWN1X2FkbWluIiwiaWQiOiIwIn0seyJuYW1lIjoidGVfYWdlbmN5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3Nic19yZXBfYWNjZWxlcmF0aW9uIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2Rpc2tBY2MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kc3NfbW9udGgiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9vYnNfZGVlcF9hcmNoaXZlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1zb3V0aC00YyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2RlY19tb250aF91c2VyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2JyX3NlbGxvdXQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3Nfb2xkX3Jlb3VyY2UiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfUm95YWx0eSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3dlbGlua2JyaWRnZV9lbmRwb2ludF9idXkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jYnJfZmlsZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rtcy1yb2NrZXRtcTUtYmFzaWMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kbXMta2Fma2EzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfb2JzX2RlY19tb250aCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NzYnNfcmVzdG9yZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Nicl92bXdhcmUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pZG1lX21ibV9mb3VuZGF0aW9uIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2M2YSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX3BjX3ZlbmRvcl9zdWJ1c2VyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbXVsdGlfYmluZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3Ntbl9jYWxsbm90aWZ5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtM2QiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3Byb2dyZXNzYmFyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2VzX3Jlc291cmNlZ3JvdXBfdGFnIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX29mZmxpbmVfYWM3IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXZzX3JldHlwZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2tvb21hcCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c19lc3NkMiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rtcy1hbXFwLWJhc2ljIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXZzX3Bvb2xfY2EiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLXNvdXRod2VzdC0yYiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2h3Y3BoIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX29mZmxpbmVfZGlza180IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaHdkZXYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zbW5fd2VsaW5rcmVkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaHZfdmVuZG9yIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfY24tbm9ydGgtNGQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfaGVjc194IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2JyX2ZpbGVzX2JhY2t1cCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19hYzciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lcHMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3Jlc3RvcmVfYWxsIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX3JvdW5kdGFibGUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfZXh0IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfcGZzX2RlZXBfYXJjaGl2ZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfYXAtc291dGhlYXN0LTFlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9ydS1tb3Njb3ctMWIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2FwLXNvdXRoZWFzdC0xZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FwcHN0YWdlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtMWYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zbW5fYXBwbGljYXRpb24iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfY29sZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3Jkc19jYSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19ncHVfZzVyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfb3BfZ2F0ZWRfbWVzc2FnZW92ZXI1ZyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19yaSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfcnUtbm9ydGh3ZXN0LTJjIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmX3BsYXRpbnVtIiwiaWQiOiIwIn1dLCJpc3N1ZWRfYXQiOiIyMDI1LTA2LTIzVDA3OjI5OjMzLjg3NTAwMFoiLCJ1c2VyIjp7ImRvbWFpbiI6eyJuYW1lIjoiQ1NETi1qaWFua2VuaW5neWFvIiwiaWQiOiIwZDhlMzRhMzcxNDc0YjJkYmJhM2M0ZDdlNDJmZWZiNiJ9LCJuYW1lIjoiSUFNMiIsInBhc3N3b3JkX2V4cGlyZXNfYXQiOiIiLCJpZCI6IjkxYjQzM2Q0N2FlYzQxNWZhYjkwNzc0ZWI5MTUwNTQ5In19fTGCAcEwggG9AgEBMIGXMIGJMQswCQYDVQQGEwJDTjESMBAGA1UECAwJR3VhbmdEb25nMREwDwYDVQQHDAhTaGVuWmhlbjEuMCwGA1UECgwlSHVhd2VpIFNvZnR3YXJlIFRlY2hub2xvZ2llcyBDby4sIEx0ZDEOMAwGA1UECwwFQ2xvdWQxEzARBgNVBAMMCmNhLmlhbS5wa2kCCQDcsytdEGFqEDALBglghkgBZQMEAgEwDQYJKoZIhvcNAQEBBQAEggEAPKkuN00GYGSO2WdrvsgelXd7GauhYBMY9HvxNt1aSIp8wjWeqyjY0NdIPcP2SHq8qjg2oDVqjZshuO3mQcexUat9eUm-ecoNC+65bbTw5HwZAskR77-G211jiQ-pxRTtfhmyBi0mY9lUteFsyLNSlxKaCRDZwIQIZ-G15ihliG7mJIjfLvEtMsaRZpKon7uqvy8tD7ddYnCWAbzUwqkb-dAHtxYaCEL7YXS8BuFBhgYJVEnF60ddaCpZnWv9l-D4tt6xXgF7hNFzlFymAphZMd3xd71f44zYKr+E5pGBo3-myhyXa8neVuyUzFNKTSbwSeBoWJ7WVG8xiJ0yZ8tkIg==";
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
        submap.put("joiner","and");
        List<PartSearchCondition> conditions = new ArrayList<>();

        //必定加入的条件，当前用户id
        //TODO 从threadlocal中获取用户id，并传入
//        Map<String, Object> claims = ThreadLocalUtil.get();
//        String userId=claims.get("id").toString();
        String userId="1";
        if(userId!=null&&!userId.isEmpty()){
            PartSearchCondition partSearchCondition = new PartSearchCondition();
            partSearchCondition.setConditionName("userId");
            partSearchCondition.setOperator("=");
            List<String> searchValues = new ArrayList<>();
            searchValues.add(userId);
            partSearchCondition.setConditionValues(searchValues);
            conditions.add(partSearchCondition);
        }

        if(partSearchDTO.getCode()!=null&& !partSearchDTO.getCode().isEmpty()){
            PartSearchCondition partSearchCondition = new PartSearchCondition();
            partSearchCondition.setConditionName("id");
            partSearchCondition.setOperator("=");
            List<String> searchValues = new ArrayList<>();
            searchValues.add(partSearchDTO.getCode());
            partSearchCondition.setConditionValues(searchValues);
            conditions.add(partSearchCondition);
        }

        if(partSearchDTO.getName()!=null&& !partSearchDTO.getName().isEmpty()){
            PartSearchCondition partSearchCondition = new PartSearchCondition();
            partSearchCondition.setConditionName("name");
            partSearchCondition.setOperator("=");
            List<String> searchValues = new ArrayList<>();
            searchValues.add(partSearchDTO.getName());
            partSearchCondition.setConditionValues(searchValues);
            conditions.add(partSearchCondition);
        }

        map.put("conditions", conditions);

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

                if(temp.get("description")!=null&&temp.get("description")!=""){
                    vo.setVersion(temp.get("version").toString());
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
        map.put("masterId",id);
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

    /*
        分页查询，暂时不知道条件查询参数
        pageSizePath 页大小
        urPagePath 第几页 从1开始
        identifier 应用标识
        modelName 模型类型

     */
//    public List<Part> list(String searchKey){
//        int pageSize = 10;
//        int curPage = 1;
//        RestTemplate restTemplate=new RestTemplate();
//        Result<List<Part>> result = restTemplate.getForObject("https://dme.cn-north-4.huaweicloud.com/" +
//                "rdm_4fc7a89107bf434faa3292b41c635750_app/publicservices/api/Part/find/{pageSize}/{curPage}", Result.class,pageSize,curPage);
//        if (result != null) {
//            return result.data;
//        }
//        return null;
//    }


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

        //TODO 分页的大小和第几页默认写死了
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
}
