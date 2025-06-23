package com.miniBOM.dao;

import com.huawei.innovation.rdm.coresdk.basic.dto.ObjectReferenceParamDTO;
import com.huawei.innovation.rdm.minibom.delegator.BOMUsesOccurrenceDelegator;
import com.huawei.innovation.rdm.minibom.dto.entity.BOMUsesOccurrenceCreateDTO;
import com.huawei.innovation.rdm.minibom.dto.entity.BOMUsesOccurrenceViewDTO;
import com.miniBOM.pojo.BOMLink;
import com.miniBOM.pojo.BOMUsesOccurrence;
import com.miniBOM.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BOMUsesOccurrenceDao {

    @Autowired
    private BOMUsesOccurrenceDelegator delegator;

    public Result create(BOMUsesOccurrence bomUsesOccurrence) {
        BOMUsesOccurrenceCreateDTO createDTO = new BOMUsesOccurrenceCreateDTO();
        ObjectReferenceParamDTO bomLink = new ObjectReferenceParamDTO();
        bomLink.setId(bomUsesOccurrence.getBomLink().getId());
        createDTO.setBomLink(bomLink);
        createDTO.setReferenceDesignator(bomUsesOccurrence.getReferenceDesignator());
        BOMUsesOccurrenceViewDTO bomUsesOccurrenceViewDTO = delegator.create(createDTO);
        BOMUsesOccurrence result = new BOMUsesOccurrence();
        BOMLink link = new BOMLink();
        result.setBomLink(link);
        result.getBomLink().setId(bomUsesOccurrenceViewDTO.getBomLink().getId());
        result.setId(bomUsesOccurrenceViewDTO.getId());
        result.setReferenceDesignator(bomUsesOccurrenceViewDTO.getReferenceDesignator());
        return Result.success(result);
    }
}
