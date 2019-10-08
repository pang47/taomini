package com.taomini.service.impl;


import com.taomini.core.dao.IIniConfigMapper;
import com.taomini.model.IniConfigDTO;
import com.taomini.service.IIniConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 *
 * 数据字典
 *
 * @author chentao
 * @create 2019/10/8
 * @since 1.0.0
 */
@Service
public class IniConfigServiceImpl implements IIniConfigService {

    @Autowired
    IIniConfigMapper iIniConfigMapper;

    @Override
    public List<IniConfigDTO> getIniConfig(String iniType, String iniClass) {

        IniConfigDTO iniConfigDTO = new IniConfigDTO();
        iniConfigDTO.setIniType(iniType);
        iniConfigDTO.setIniClass(iniClass);
        List<IniConfigDTO> list = iIniConfigMapper.getIniConfig(iniConfigDTO);

        return list;
    }
}