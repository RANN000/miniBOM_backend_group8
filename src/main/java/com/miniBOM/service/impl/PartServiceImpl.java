package com.miniBOM.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miniBOM.dao.PartDao;
import com.miniBOM.pojo.*;
import com.miniBOM.pojo.Part.Branch;
import com.miniBOM.pojo.Part.Master;
import com.miniBOM.pojo.Part.Part;
import com.miniBOM.pojo.Part.PartCategoryAttr.PartCategoryAttrReqVO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateReqDTO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateVO;
import com.miniBOM.pojo.Part.PartCreate.PartCreateDTO;
import com.miniBOM.pojo.Part.PartHistory.PartHistoryResDTO;
import com.miniBOM.pojo.Part.PartHistory.PartHistoryVO;
import com.miniBOM.pojo.Part.PartSearch.PartSearchReqVO;
import com.miniBOM.pojo.Part.PartSearch.PartSearchDTO;
import com.miniBOM.pojo.Part.PartSearch.PartSerchVO;
import com.miniBOM.pojo.Part.PartUpdate.PartUpdateDTO;
import com.miniBOM.pojo.Part.PartUpdate.PartUpdateReqDTO;
import com.miniBOM.service.PartService;
import com.miniBOM.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;



@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartDao partDao;

    @Override
    public PartCreateVO add(PartCreateDTO partDTO) {

        PartCreateReqDTO partCreateDTO = new PartCreateReqDTO();
        if (partDTO.getCode() != null) {
            partCreateDTO.setId(partDTO.getCode());
        }
        if (partDTO.getName() != null && !partDTO.getName().isEmpty()) {
            partCreateDTO.setName(partDTO.getName());
        }
        if (partDTO.getDefaultUnit() != null) {
            partCreateDTO.setDefaultUnit(partDTO.getDefaultUnit());
        }
        //TODO TODO 从threadlocal中获取用户id，并传入
//        Map<String, Object> claims = ThreadLocalUtil.get();
//        partCreateDTO.setUserId(claims.get("id").toString());
        partCreateDTO.setUserId("1");
        Master master = new Master();

        Branch branch = new Branch();

        if(partDTO.getCode()!=null&& !partDTO.getCode().isEmpty()){
            master.setId(partDTO.getCode());
            branch.setId(partDTO.getCode());
        }else{
            long timestamp = System.currentTimeMillis();

            // 2. 生成5位随机数（范围：10000 ~ 99999）
            int randomNum = ThreadLocalRandom.current().nextInt(10000, 100000);

            // 3. 拼接成18位数字字符串
            String id = String.valueOf(timestamp) + randomNum;
            master.setId(id);
            branch.setId(id);
        }

        partCreateDTO.setMaster(master);
        partCreateDTO.setBranch(branch);
        if(partDTO.getCategory() != null&& !partDTO.getCategory().isEmpty()) {
            //拓展属性中加入分类
            List<Pair> extAttrs = new ArrayList<>();
            extAttrs.add(new Pair("Classcification", partDTO.getCategory()));
            partCreateDTO.setExtAttrs(extAttrs);
            if(partDTO.getAttributes() != null&& !partDTO.getAttributes().isEmpty()){

                //加入分类的属性
                List<Map<String,Map<String,String>>> attrs = new ArrayList<>();
                Map<String, String> attrSubMap=new HashMap<>();
                for (Map.Entry<String, String> entry : partDTO.getAttributes().entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    attrSubMap.put(key, value);
                }
                Map<String,Map<String,String>> attrMap = new HashMap<>();

                attrMap.put("Classcification",attrSubMap);
                attrs.add(attrMap);
                partCreateDTO.setClsAttrs(attrs);
            }
        }

        try {
            return partDao.add(partCreateDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PartSerchVO> find(PartSearchDTO partSearchDTO) {

        List<PartSearchReqVO> partSearchReqVOList = null;

        try {
            partSearchReqVOList = partDao.find(partSearchDTO);
            System.out.println(partSearchReqVOList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<PartSerchVO> partSearchList = new ArrayList<>();
        if(partSearchReqVOList!=null&& !partSearchReqVOList.isEmpty()){
            for (PartSearchReqVO partSearchReqVO : partSearchReqVOList) {

                PartSerchVO partSerchVO = new PartSerchVO();
                partSerchVO.setCode(partSearchReqVO.getId());
                partSerchVO.setName(partSearchReqVO.getName());
                partSerchVO.setUserId(partSearchReqVO.getUserId());

                if(partSearchReqVO.getVersion()!=null&& partSearchReqVO.getIteration()!=null){
                    partSerchVO.setVersion(partSearchReqVO.getVersion() + "." + partSearchReqVO.getIteration());
                }
                if(partSearchReqVO.getExtAttrs()!=null&&!partSearchReqVO.getExtAttrs().isEmpty()){
//                    System.out.println("extattrs:"+partSearchReqVO.getExtAttrs());
                    //List<Map<String,Map<String,String>>>
                    for (Map<String,Object> map : partSearchReqVO.getExtAttrs()) {
                        System.out.println(map.toString());
                        if (map.get("name").equals("Classcification")) {
                            //Map<String,String>
                            Map<String,String> classcificaitonAttr=(Map<String,String>)map.get("value");
                            //value可能为null
                            if(classcificaitonAttr!=null && !classcificaitonAttr.isEmpty()){
                                partSerchVO.setCategory(classcificaitonAttr.get("id"));
                            }
                        }
                    }
                }

                //从clsattr中获取attribute
                List<Map<String,Map<String,String>>> clsAttributes = partSearchReqVO.getClsAttrs();
                if(clsAttributes!=null&&!clsAttributes.isEmpty()){
                    Map<String,Map<String,String>>attributesMap=clsAttributes.get(0);
                    Map<String, String> attributes=attributesMap.get("Classcification");
                    partSerchVO.setAttributes(attributes);
                }
                if (partSearchDTO.getCategory() != null && !partSearchDTO.getCategory().isEmpty()) {
                    //删除和需要的类型不匹配的数据
                    if(partSerchVO.getCategory()==null||!partSerchVO.getCategory().equals(partSearchDTO.getCategory())){
                        continue;
                    }

                }
                partSearchList.add(partSerchVO);
            }
        }
        //存在缺陷，在service判断，则分页和dao分页不符合，基于dao分页后的数据再进行筛选
        return partSearchList;
    }

    @Override
    public List<PartHistoryVO> listAllVersion(String masterId) {
        List<PartHistoryResDTO> historyResDTOS = partDao.listAllVersion(masterId);
        List<PartHistoryVO> partHistoryVOS = new ArrayList<>();
        for (PartHistoryResDTO partHistoryResDTO : historyResDTOS) {
            PartHistoryVO partHistoryVO = new PartHistoryVO();
            partHistoryVO.setVersion(partHistoryResDTO.getVersion() + "." + partHistoryResDTO.getIteration());
            partHistoryVO.setUpdatedAt(partHistoryResDTO.getLastUpdateTime());
            partHistoryVOS.add(partHistoryVO);
        }
        return partHistoryVOS;
    }
//
//    @Override
//    public List<Part> list(String searchKey) {
//        return partDao.list(searchKey);
//    }

    @Override
    public List<String> listCategoryAttr(String categoryId) {
        List<String> attrList = new ArrayList<>();
        List<PartCategoryAttrReqVO> attrReqList = partDao.listCategoryAttr(categoryId);
        for (PartCategoryAttrReqVO partCategoryAttrReqVO : attrReqList) {
            attrList.add(partCategoryAttrReqVO.getName());
        }
        return attrList;
    }

    @Override
    public void update(PartUpdateDTO partUpdateDTO) {
        String masterId = partUpdateDTO.getCode();
        partDao.checkOut(masterId);

        PartUpdateReqDTO partUpdateReqDTO = new PartUpdateReqDTO();
        //TODO 为空如何抛异常
        if (partUpdateDTO.getCode() != null && !partUpdateDTO.getCode().isEmpty()) {
            partUpdateReqDTO.setId(partUpdateDTO.getCode());
        }
        if (partUpdateDTO.getName() != null && !partUpdateDTO.getName().isEmpty()) {
            partUpdateReqDTO.setName(partUpdateDTO.getName());
        }
        //分类通过category修改
        if(partUpdateDTO.getCategory() != null && !partUpdateDTO.getCategory().isEmpty()) {
            List<Pair> list=new ArrayList<>();
            list.add(new Pair("Classcification", partUpdateDTO.getCategory()));
            partUpdateReqDTO.setExtAttrs(list);
        }
        //修改属性
        List<Map<String,Map<String,String >>> clsAttrs = new ArrayList<>();
        if (partUpdateDTO.getAttributes() != null && !partUpdateDTO.getAttributes().isEmpty()) {

            //直接使用map
            Map<String,Map<String,String >> map = new HashMap<>();
            map.put("Classcification", partUpdateDTO.getAttributes());
            clsAttrs.add(map);
            partUpdateReqDTO.setClsAttrs(clsAttrs);
        }
        System.out.println("partUpdateReqDTO"+partUpdateReqDTO);
        partDao.update(partUpdateReqDTO);

        partDao.checkIn(masterId);
    }

    @Override
    public void checkOut(String masterId) {
        partDao.checkOut(masterId);
    }

    @Override
    public void checkIn(String masterId) {
        partDao.checkIn(masterId);
    }

    @Override
    public void delete(String id) {
        partDao.delete(id);
    }
}
