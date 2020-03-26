package com.taomini.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taomini.core.constant.IniConfigEnum;
import com.taomini.core.dao.ITransRecordMapper;
import com.taomini.model.IniConfigDTO;
import com.taomini.model.TransRecordDTO;
import com.taomini.service.ILimitService;
import com.taomini.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class LimitListServiceImpl implements ILimitService {

    private static final Logger logger = LoggerFactory.getLogger(LimitListServiceImpl.class);

    @Autowired
    IniConfigServiceImpl iniConfigService;

    @Autowired
    ITransRecordMapper transRecordMapper;

    @Override
    public JSONArray getLimitList() {

        List<IniConfigDTO> limitInis = iniConfigService.getIniConfig(IniConfigEnum.LIMITTYPE.getIniType(), IniConfigEnum.LIMITTYPE.getIniClass());
        limitInis.sort(Comparator.comparing( dto-> dto.getIniDesc()));

        List<IniConfigDTO> transInis = iniConfigService.getIniConfig(IniConfigEnum.TRANSTYPE.getIniType(), IniConfigEnum.TRANSTYPE.getIniClass());

        List<TransRecordDTO> sumTrans = transRecordMapper.getTransRecordMonthByTransType(DateUtil.getCurrDate().substring(0, 6));

        JSONArray arr = new JSONArray();

        limitInis.forEach(dto -> {
            JSONObject obj = new JSONObject();
            transInis.forEach(iniConfigDTO -> {
                if (iniConfigDTO.getIniCode().equals(dto.getIniCode()))
                    obj.put("transTypeName", iniConfigDTO.getIniCodeValue());
            });

            obj.put("transTypeId", dto.getIniCode());
            sumTrans.forEach(transRecordDTO -> {
                if (transRecordDTO.getTransType().equals(dto.getIniCode()))
                    obj.put("limit", Double.parseDouble(dto.getIniCodeValue()) - Double.parseDouble(transRecordDTO.getMoney()));
            });
            arr.add(obj);
        });

        return arr;
    }
}
