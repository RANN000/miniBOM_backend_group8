package com.miniBOM.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huawei.innovation.rdm.coresdk.basic.dto.*;
import com.huawei.innovation.rdm.coresdk.basic.dto.GenericLinkQueryDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.ObjectReferenceParamDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.minibom.delegator.BOMLinkDelegator;
import com.huawei.innovation.rdm.minibom.delegator.PartDelegator;
import com.huawei.innovation.rdm.minibom.dto.entity.PartViewDTO;
import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkCreateDTO;
import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkUpdateDTO;
import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkViewDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreatePartDTO;
import com.miniBOM.pojo.Bom.BOMCreate.BOMCreateVO;
import com.miniBOM.pojo.Bom.BOMSearch.BOMRootVo;
import com.miniBOM.pojo.Bom.BOMSearch.BOMShowFatherVO;
import com.miniBOM.pojo.Bom.BOMDelete.BOMDeleteDTO;
import com.miniBOM.pojo.Bom.BOMShow.BOMShowVO;
import com.miniBOM.pojo.Bom.BOMUpdate.BOMUpdateDTO;
import com.miniBOM.pojo.Bom.BOMUpdate.BOMUpdateVO;
import com.miniBOM.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class BOMDao {

    public String applicationId="4fc7a89107bf434faa3292b41c635750";
    public String token=
            "MIIN2gYJKoZIhvcNAQcCoIINyzCCDccCAQExDTALBglghkgBZQMEAgEwggvsBgkqhkiG9w0BBwGgggvdBIIL2XsidG9rZW4iOnsiZXhwaXJlc19hdCI6IjIwMjUtMDYtMjRUMDc6Mjk6MzMuODc1MDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwiZG9tYWluIjp7Im5hbWUiOiJDU0ROLWppYW5rZW5pbmd5YW8iLCJpZCI6IjBkOGUzNGEzNzE0NzRiMmRiYmEzYzRkN2U0MmZlZmI2In0sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6IjAifSx7Im5hbWUiOiJzZWN1X2FkbWluIiwiaWQiOiIwIn0seyJuYW1lIjoidGVfYWdlbmN5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3Nic19yZXBfYWNjZWxlcmF0aW9uIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2Rpc2tBY2MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kc3NfbW9udGgiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9vYnNfZGVlcF9hcmNoaXZlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1zb3V0aC00YyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2RlY19tb250aF91c2VyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2JyX3NlbGxvdXQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3Nfb2xkX3Jlb3VyY2UiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfUm95YWx0eSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3dlbGlua2JyaWRnZV9lbmRwb2ludF9idXkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jYnJfZmlsZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rtcy1yb2NrZXRtcTUtYmFzaWMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kbXMta2Fma2EzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfb2JzX2RlY19tb250aCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NzYnNfcmVzdG9yZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Nicl92bXdhcmUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pZG1lX21ibV9mb3VuZGF0aW9uIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2M2YSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX3BjX3ZlbmRvcl9zdWJ1c2VyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbXVsdGlfYmluZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3Ntbl9jYWxsbm90aWZ5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtM2QiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3Byb2dyZXNzYmFyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2VzX3Jlc291cmNlZ3JvdXBfdGFnIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX29mZmxpbmVfYWM3IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXZzX3JldHlwZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2tvb21hcCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2V2c19lc3NkMiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rtcy1hbXFwLWJhc2ljIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXZzX3Bvb2xfY2EiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLXNvdXRod2VzdC0yYiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2h3Y3BoIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX29mZmxpbmVfZGlza180IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaHdkZXYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zbW5fd2VsaW5rcmVkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaHZfdmVuZG9yIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfY24tbm9ydGgtNGQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfaGVjc194IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2JyX2ZpbGVzX2JhY2t1cCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19hYzciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lcHMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3Jlc3RvcmVfYWxsIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX3JvdW5kdGFibGUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfZXh0IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfcGZzX2RlZXBfYXJjaGl2ZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfYXAtc291dGhlYXN0LTFlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9ydS1tb3Njb3ctMWIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2FwLXNvdXRoZWFzdC0xZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FwcHN0YWdlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtMWYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zbW5fYXBwbGljYXRpb24iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfY29sZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3Jkc19jYSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19ncHVfZzVyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfb3BfZ2F0ZWRfbWVzc2FnZW92ZXI1ZyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19yaSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfcnUtbm9ydGh3ZXN0LTJjIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmX3BsYXRpbnVtIiwiaWQiOiIwIn1dLCJpc3N1ZWRfYXQiOiIyMDI1LTA2LTIzVDA3OjI5OjMzLjg3NTAwMFoiLCJ1c2VyIjp7ImRvbWFpbiI6eyJuYW1lIjoiQ1NETi1qaWFua2VuaW5neWFvIiwiaWQiOiIwZDhlMzRhMzcxNDc0YjJkYmJhM2M0ZDdlNDJmZWZiNiJ9LCJuYW1lIjoiSUFNMiIsInBhc3N3b3JkX2V4cGlyZXNfYXQiOiIiLCJpZCI6IjkxYjQzM2Q0N2FlYzQxNWZhYjkwNzc0ZWI5MTUwNTQ5In19fTGCAcEwggG9AgEBMIGXMIGJMQswCQYDVQQGEwJDTjESMBAGA1UECAwJR3VhbmdEb25nMREwDwYDVQQHDAhTaGVuWmhlbjEuMCwGA1UECgwlSHVhd2VpIFNvZnR3YXJlIFRlY2hub2xvZ2llcyBDby4sIEx0ZDEOMAwGA1UECwwFQ2xvdWQxEzARBgNVBAMMCmNhLmlhbS5wa2kCCQDcsytdEGFqEDALBglghkgBZQMEAgEwDQYJKoZIhvcNAQEBBQAEggEAPKkuN00GYGSO2WdrvsgelXd7GauhYBMY9HvxNt1aSIp8wjWeqyjY0NdIPcP2SHq8qjg2oDVqjZshuO3mQcexUat9eUm-ecoNC+65bbTw5HwZAskR77-G211jiQ-pxRTtfhmyBi0mY9lUteFsyLNSlxKaCRDZwIQIZ-G15ihliG7mJIjfLvEtMsaRZpKon7uqvy8tD7ddYnCWAbzUwqkb-dAHtxYaCEL7YXS8BuFBhgYJVEnF60ddaCpZnWv9l-D4tt6xXgF7hNFzlFymAphZMd3xd71f44zYKr+E5pGBo3-myhyXa8neVuyUzFNKTSbwSeBoWJ7WVG8xiJ0yZ8tkIg==";

    @Autowired
    BOMLinkDelegator delegator;

    @Autowired
    PartDelegator partDelegator;

    @Autowired
    PartService partService;


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

    //BOM查询所有子项
    //TODO 无子项处理
    public List<BOMShowVO> show(Long sourceId) throws JsonProcessingException {

        GenericLinkQueryDTO queryDTO = new GenericLinkQueryDTO();
        queryDTO.setObjectId(sourceId);
        queryDTO.setRole("source");
        queryDTO.setLatestOnly(true);
        RDMPageVO page = new RDMPageVO();
        page.setCurPage(1);
        page.setPageSize(1000);
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
                if(temp.getTarget().getName()!=null){
                    BOMShowVO.setTargetName(temp.getTarget().getName());
                }

                BOMShowVOList.add(BOMShowVO);

            }
        }

        return BOMShowVOList;
    }


    //查找父项
    public BOMShowFatherVO showFather(Long partId) {
        GenericLinkQueryDTO queryDTO = new GenericLinkQueryDTO();
        queryDTO.setObjectId(partId);
        queryDTO.setRole("target");
        queryDTO.setLatestOnly(true);
        RDMPageVO page = new RDMPageVO();
        page.setCurPage(1);
        page.setPageSize(1000);
        List<BOMLinkViewDTO> bomLinkList = delegator.queryRelationship(queryDTO, page);

        if(bomLinkList == null || bomLinkList.isEmpty()){
            return null;
        }

        BOMLinkViewDTO result = bomLinkList.get(0);
        BOMShowFatherVO bomShowFatherVO = new BOMShowFatherVO();
        bomShowFatherVO.setBOMLinkId(result.getId());
        bomShowFatherVO.setSequenceNumber(result.getSequenceNumber());
        bomShowFatherVO.setQuantity(result.getQuantity());
        bomShowFatherVO.setReferenceDesignator(result.getReferenceDesignator());
        bomShowFatherVO.setSourceId(result.getSource().getId());
        bomShowFatherVO.setSourceName(result.getSource().getName());
        return bomShowFatherVO;
    }

    //查找根part
    public BOMShowFatherVO showRoot(Long partId) {
        BOMShowFatherVO bomShowFatherVO = showFather(partId);
        if(bomShowFatherVO==null){
            return bomShowFatherVO;
        }
        bomShowFatherVO = showFather(bomShowFatherVO.getSourceId());
        return bomShowFatherVO;
    }

    //根part转格式
    public BOMRootVo toRoot(BOMShowFatherVO bomShowFatherVO){
        BOMRootVo bomRootVO = new BOMRootVo();
        bomRootVO.setPartId(bomShowFatherVO.getSourceId());
        bomRootVO.setPartName(bomShowFatherVO.getSourceName());
        return bomRootVO;
    }

    //根据partId查找partName
    public String findPartNameById(Long partId){
        PartDelegator partDelegator = new PartDelegator();
        PersistObjectIdDecryptDTO partDTO = new PersistObjectIdDecryptDTO();
        partDTO.setId(partId);
        PartViewDTO partViewDTO = partDelegator.get(partDTO);
        return partViewDTO.getName();
    }

    public BOMCreateVO addPart(BOMCreatePartDTO bomCreatePartDTO)throws JsonProcessingException {

        BOMCreateVO  bomCreateVO = new BOMCreateVO();

        BOMLinkCreateDTO bomLinkCreateDTO = new BOMLinkCreateDTO();

        ObjectReferenceParamDTO sourceDTO =new ObjectReferenceParamDTO();

        sourceDTO.setId(bomCreatePartDTO.getSourceId());


        ObjectReferenceParamDTO targetDTO =new ObjectReferenceParamDTO();

        //没有targetId说明需要创建子对象，先创建子对象，再给出masterId/targetId

            Map<String,Object>map=partService.add(bomCreatePartDTO.getPartCreateDTO());
            String masterId=map.get("masterId").toString();
            String name=map.get("name").toString();
            targetDTO.setId(Long.parseLong(masterId));

        bomLinkCreateDTO.setSource(sourceDTO);
        bomLinkCreateDTO.setTarget(targetDTO);



        bomLinkCreateDTO.setSequenceNumber(bomCreatePartDTO.getSequenceNumber());
        bomLinkCreateDTO.setReferenceDesignator(bomCreatePartDTO.getReferenceDesignator());
        bomLinkCreateDTO.setQuantity(bomCreatePartDTO.getQuantity());

        System.out.println("bomLinkCreateDTO:"+bomLinkCreateDTO);
        BOMLinkViewDTO bomLinkViewDTO= delegator.create(bomLinkCreateDTO);
        System.out.println("bomLinkViewDTO:"+bomLinkViewDTO);


        bomCreateVO.setId(bomLinkViewDTO.getId());

        bomCreateVO.setSequenceNumber(bomLinkViewDTO.getSequenceNumber());
        bomCreateVO.setReferenceDesignator(bomLinkViewDTO.getReferenceDesignator());
        bomCreateVO.setQuantity(bomLinkViewDTO.getQuantity());
        bomCreateVO.setSourceId(bomLinkViewDTO.getSource().getId());
        System.out.println("bomLinkViewDTO.getSource().getId():"+bomLinkViewDTO.getSource().getId());
        bomCreateVO.setSourceName(bomLinkViewDTO.getSource().getName());
        System.out.println("bomLinkViewDTO.getSource().getName()"+bomLinkViewDTO.getSource().getName());
        bomCreateVO.setTargetId(bomLinkViewDTO.getTarget().getId());
        System.out.println("bomLinkViewDTO.getTarget().getId():"+bomLinkViewDTO.getTarget().getId());
        bomCreateVO.setTargetName(bomLinkViewDTO.getTarget().getName());
        System.out.println("bomLinkViewDTO.getTarget().getName()"+bomLinkViewDTO.getTarget().getName());
//        bomCreateVO.setTargetName(name);
        return bomCreateVO;

    }

    public BOMUpdateVO update(BOMUpdateDTO bomUpdateDTO) {

        BOMLinkUpdateDTO bomLinkUpdateDTO = new BOMLinkUpdateDTO();
        bomLinkUpdateDTO.setId(bomUpdateDTO.getBomLinkId());
        bomLinkUpdateDTO.setSequenceNumber(bomUpdateDTO.getSequenceNumber());
        bomLinkUpdateDTO.setQuantity(bomUpdateDTO.getQuantity());
        bomLinkUpdateDTO.setReferenceDesignator(bomUpdateDTO.getReferenceDesignator());

        System.out.println("bomLinkUpdateDTO:"+bomLinkUpdateDTO);
        BOMLinkViewDTO bomLinkViewDTO = delegator.update(bomLinkUpdateDTO);

        BOMUpdateVO  bomUpdateVO = new BOMUpdateVO();

        bomUpdateVO.setId(bomLinkViewDTO.getId());
        bomUpdateVO.setSequenceNumber(bomLinkViewDTO.getSequenceNumber());
        bomUpdateVO.setQuantity(bomLinkViewDTO.getQuantity());
        bomUpdateVO.setReferenceDesignator(bomLinkViewDTO.getReferenceDesignator());
        bomUpdateVO.setSourceId(bomLinkViewDTO.getSource().getId());
        bomUpdateVO.setSourceName(bomLinkViewDTO.getSource().getName());
        bomUpdateVO.setTargetId(bomLinkViewDTO.getTarget().getId());
        bomUpdateVO.setTargetName(bomLinkViewDTO.getTarget().getName());

        System.out.println("bomUpdateVO:"+bomUpdateVO);
        return bomUpdateVO;

    }

    public void delete(BOMDeleteDTO bomDeleteDTO) throws JsonProcessingException {
        System.out.println(bomDeleteDTO);
        if(bomDeleteDTO==null) return;


        //根据BOMLinkId查找上下游
        //有BOMLinkId一定有targetId
        Long id=bomDeleteDTO.getBomLinkId();
        System.out.println("bomDeleteDTO.getId():"+id);
        PersistObjectIdDecryptDTO  persistObjectIdDecryptDTO = new PersistObjectIdDecryptDTO();
        persistObjectIdDecryptDTO.setId(id);
        persistObjectIdDecryptDTO.setDecrypt(true);
        System.out.println("persistObjectIdDecryptDTO.getId():"+persistObjectIdDecryptDTO.getId());
        BOMLinkViewDTO bomLinkViewDTO = delegator.get(persistObjectIdDecryptDTO);
        System.out.println("bomLinkViewDTO:"+bomLinkViewDTO);
        //根据targetId查找它的BOMLink
        List<BOMShowVO> list=show(bomLinkViewDTO.getTarget().getId());
        System.out.println(list);
        //查询
        if(list!=null){
            for(BOMShowVO boMShowVO:list){
                //通过targetId作为sourceId继续向下找BOMLinkId
                BOMDeleteDTO tempDeleteDTO = new BOMDeleteDTO();
                tempDeleteDTO.setBomLinkId(boMShowVO.getBOMLinkId());
                //递归
                delete(tempDeleteDTO);
                //递归结束开始删除节点

                //删除part
                MasterIdModifierDTO masterIdModifierDTO = new MasterIdModifierDTO();
                masterIdModifierDTO.setMasterId(bomLinkViewDTO.getTarget().getId());
                partDelegator.delete(masterIdModifierDTO);

                //删除BOMLink关系
                PersistObjectIdModifierDTO persistObjectIdModifierDTO = new PersistObjectIdModifierDTO();
                persistObjectIdModifierDTO.setId(bomDeleteDTO.getBomLinkId());
                delegator.delete(persistObjectIdModifierDTO);


            }
        }

    }
}
