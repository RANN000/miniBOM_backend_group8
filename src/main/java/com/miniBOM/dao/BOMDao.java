package com.miniBOM.dao;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.innovation.rdm.coresdk.basic.dto.GenericLinkQueryDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.ObjectReferenceParamDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.minibom.delegator.BOMLinkDelegator;
import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkCreateDTO;
import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkViewDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateVO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowDTO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowVO;
import com.miniBOM.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class BOMDao {

    public String applicationId="4fc7a89107bf434faa3292b41c635750";
    public String token=
            "MIIN2gYJKoZIhvcNAQcCoIINyzCCDccCAQExDTALBglghkgBZQMEAgEwggvsBgkqhkiG9w0BBwGgggvdBIIL2XsidG9rZW4iOnsiZXhwaXJlc19hdCI6IjIwMjUtMDYtMjRUMDc6Mjk6MzMuODc1MDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwiZG9tYWluIjp7Im5hbWUiOiJDU0ROLWppYW5rZW5pbmd5YW8iLCJpZCI6IjBkOGUzNGEzNzE0NzRiMmRiYmEzYzRkN2U0MmZlZmI2In0sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6IjAifSx7Im5hbWUiOiJzZWN1X2FkbWluIiwiaWQiOiIwIn0seyJuYW1lIjoidGVfYWdlbmN5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3Nic19yZXBfYWNjZWxlcmF0aW9uIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2Rpc2tBY2MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kc3NfbW9udGgiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9vYnNfZGVlcF9hcmNoaXZlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1zb3V0aC00YyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2RlY19tb250aF91c2VyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2JyX3NlbGxvdXQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3Nfb2xkX3Jlb3VyY2UiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfUm95YWx0eSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3dlbGlua2JyaWRnZV9lbmRwb2ludF9idXkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jYnJfZmlsZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rtcy1yb2NrZXRtcTUtYmFzaWMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kbXMta2Fma2EzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfb2JzX2RlY19tb250aCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NzYnNfcmVzdG9yZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Nicl92bXdhcmUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pZG1lX21ibV9mb3VuZGF0aW9uIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2M2YSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX3BjX3ZlbmRvcl9zdWJ1c2VyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbXVsdGlfYmluZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3Ntbl9jYWxsbm90aWZ5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtM2QiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3Byb2dyZXNzYmFyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2VzX3Jlc291cmNlZ3JvdXBfdGFnIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX29mZmxpbmVfYWM3IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXZzX3JldHlwZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2tvb21hcCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c19lc3NkMiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rtcy1hbXFwLWJhc2ljIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXZzX3Bvb2xfY2EiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLXNvdXRod2VzdC0yYiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2h3Y3BoIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX29mZmxpbmVfZGlza180IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaHdkZXYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zbW5fd2VsaW5rcmVkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaHZfdmVuZG9yIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfY24tbm9ydGgtNGQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfaGVjc194IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2JyX2ZpbGVzX2JhY2t1cCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19hYzciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lcHMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3Jlc3RvcmVfYWxsIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX3JvdW5kdGFibGUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfZXh0IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfcGZzX2RlZXBfYXJjaGl2ZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfYXAtc291dGhlYXN0LTFlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9ydS1tb3Njb3ctMWIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2FwLXNvdXRoZWFzdC0xZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FwcHN0YWdlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtMWYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zbW5fYXBwbGljYXRpb24iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfY29sZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3Jkc19jYSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19ncHVfZzVyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfb3BfZ2F0ZWRfbWVzc2FnZW92ZXI1ZyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19yaSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfcnUtbm9ydGh3ZXN0LTJjIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmX3BsYXRpbnVtIiwiaWQiOiIwIn1dLCJpc3N1ZWRfYXQiOiIyMDI1LTA2LTIzVDA3OjI5OjMzLjg3NTAwMFoiLCJ1c2VyIjp7ImRvbWFpbiI6eyJuYW1lIjoiQ1NETi1qaWFua2VuaW5neWFvIiwiaWQiOiIwZDhlMzRhMzcxNDc0YjJkYmJhM2M0ZDdlNDJmZWZiNiJ9LCJuYW1lIjoiSUFNMiIsInBhc3N3b3JkX2V4cGlyZXNfYXQiOiIiLCJpZCI6IjkxYjQzM2Q0N2FlYzQxNWZhYjkwNzc0ZWI5MTUwNTQ5In19fTGCAcEwggG9AgEBMIGXMIGJMQswCQYDVQQGEwJDTjESMBAGA1UECAwJR3VhbmdEb25nMREwDwYDVQQHDAhTaGVuWmhlbjEuMCwGA1UECgwlSHVhd2VpIFNvZnR3YXJlIFRlY2hub2xvZ2llcyBDby4sIEx0ZDEOMAwGA1UECwwFQ2xvdWQxEzARBgNVBAMMCmNhLmlhbS5wa2kCCQDcsytdEGFqEDALBglghkgBZQMEAgEwDQYJKoZIhvcNAQEBBQAEggEAPKkuN00GYGSO2WdrvsgelXd7GauhYBMY9HvxNt1aSIp8wjWeqyjY0NdIPcP2SHq8qjg2oDVqjZshuO3mQcexUat9eUm-ecoNC+65bbTw5HwZAskR77-G211jiQ-pxRTtfhmyBi0mY9lUteFsyLNSlxKaCRDZwIQIZ-G15ihliG7mJIjfLvEtMsaRZpKon7uqvy8tD7ddYnCWAbzUwqkb-dAHtxYaCEL7YXS8BuFBhgYJVEnF60ddaCpZnWv9l-D4tt6xXgF7hNFzlFymAphZMd3xd71f44zYKr+E5pGBo3-myhyXa8neVuyUzFNKTSbwSeBoWJ7WVG8xiJ0yZ8tkIg==";

    @Autowired
    BOMLinkDelegator delegator;

    public BOMCreateVO add(BOMCreateDTO bomCreateDTO) throws JsonProcessingException {

        BOMCreateVO  bomCreateVO = new BOMCreateVO();

        BOMLinkCreateDTO bomLinkCreateDTO = new BOMLinkCreateDTO();
        bomLinkCreateDTO.setSequenceNumber(bomCreateDTO.getSequenceNumber());
        bomLinkCreateDTO.setQuantity(bomCreateDTO.getQuantity());
        bomLinkCreateDTO.setReferenceDesignator(bomCreateDTO.getReferenceDesignator());

        ObjectReferenceParamDTO sourceDTO =new ObjectReferenceParamDTO();
        sourceDTO.setId(bomCreateDTO.getSourceId());


        ObjectReferenceParamDTO targetDTO =new ObjectReferenceParamDTO();
        targetDTO.setId(bomCreateDTO.getTargetId());

        bomLinkCreateDTO.setSource(sourceDTO);
        bomLinkCreateDTO.setTarget(targetDTO);

        BOMLinkViewDTO bomLinkViewDTO= delegator.create(bomLinkCreateDTO);
        //TODO 数据迁移
        bomCreateVO.setId(bomLinkViewDTO.getId());
        bomCreateVO.setSequenceNumber(bomLinkViewDTO.getSequenceNumber());
        bomCreateVO.setQuantity(bomLinkViewDTO.getQuantity());
        bomCreateVO.setReferenceDesignator(bomLinkViewDTO.getReferenceDesignator());
        bomCreateVO.setTargetName(bomLinkViewDTO.getTarget().getName());

        return bomCreateVO;
    }
    //BOMSearch
    public List<BOMShowVO> show(Long sourceId) throws JsonProcessingException {

        GenericLinkQueryDTO queryDTO = new GenericLinkQueryDTO();
        queryDTO.setObjectId(sourceId);
        queryDTO.setRole("source");
        queryDTO.setLatestOnly(true);
        RDMPageVO page = new RDMPageVO();
        page.setCurPage(1);
        page.setPageSize(10);
        List<BOMLinkViewDTO> bomLinkList = delegator.queryRelationship(queryDTO, page);

        List<BOMShowVO> BOMShowVOList = new ArrayList<>();

        if(bomLinkList!=null &&!bomLinkList.isEmpty()){
            for(BOMLinkViewDTO temp:bomLinkList){

                BOMShowVO BOMShowVO = new BOMShowVO();

                if(temp.getId()!=null){
                    BOMShowVO.setBOMLinkId(temp.getId());
                }
                if(temp.getQuantity()!=null){
                    BOMShowVO.setQuantity(temp.getQuantity());
                }
                if(temp.getSequenceNumber()!=null){
                    BOMShowVO.setSequenceNumber(temp.getSequenceNumber());
                }
                if(temp.getReferenceDesignator()!=null){
                    BOMShowVO.setReferenceDesignator(temp.getReferenceDesignator());
                }
                if(temp.getTarget().getId()!=null){
                    BOMShowVO.setTargetId(temp.getTarget().getId());
                }

                BOMShowVOList.add(BOMShowVO);

            }
        }

        return BOMShowVOList;
    }
}
