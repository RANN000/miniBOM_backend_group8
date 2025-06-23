package com.miniBOM.dao;

import com.huawei.innovation.rdm.coresdk.basic.dto.ObjectReferenceParamDTO;
import com.huawei.innovation.rdm.minibom.delegator.BOMLinkDelegator;
import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkCreateDTO;
import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkViewDTO;
import com.miniBOM.pojo.BOMLink;
import com.miniBOM.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BOMLinkDao {

    @Autowired
    BOMLinkDelegator bomLinkDelegator;

    public Result create(BOMLink bomLink) {
        BOMLinkCreateDTO createDTO = new BOMLinkCreateDTO();
        createDTO.setQuantity(bomLink.getQuantity());
        createDTO.setSequenceNumber(bomLink.getSequenceNumber());
        ObjectReferenceParamDTO source = new ObjectReferenceParamDTO();
        source.setId(bomLink.getSourceID());
        createDTO.setSource(source);
        ObjectReferenceParamDTO target = new ObjectReferenceParamDTO();
        target.setId(bomLink.getTargetID());
        createDTO.setTarget(target);
        BOMLinkViewDTO bomLinkViewDTO = bomLinkDelegator.create(createDTO);
        BOMLink result = new BOMLink();
        result.setId(bomLinkViewDTO.getId());
        result.setSequenceNumber(bomLinkViewDTO.getSequenceNumber());
        result.setQuantity(bomLinkViewDTO.getQuantity());
        result.setSourceID(bomLinkViewDTO.getSource().getId());
        result.setTargetID(bomLinkViewDTO.getTarget().getId());
        return Result.success(result);
    }
}
