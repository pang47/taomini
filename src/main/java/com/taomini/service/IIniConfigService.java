package com.taomini.service;

import com.taomini.model.IniConfigDTO;

import java.util.List;

public interface IIniConfigService {

    List<IniConfigDTO> getIniConfig(String iniType, String iniClass);

    IniConfigDTO getIniConfig4One(String iniType, String iniClass, String iniCode);

}
