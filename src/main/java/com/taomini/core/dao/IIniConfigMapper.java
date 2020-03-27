package com.taomini.core.dao;

import com.taomini.model.IniConfigDTO;

import java.util.List;

public interface IIniConfigMapper {
    List<IniConfigDTO> getIniConfig(IniConfigDTO iniConfigDTO);

    IniConfigDTO getIniConfig4One(IniConfigDTO iniConfigDTO);

    int updateIniConifg(IniConfigDTO iniConfigDTO);
}
