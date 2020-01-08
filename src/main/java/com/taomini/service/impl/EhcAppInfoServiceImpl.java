package com.taomini.service.impl;

import com.taomini.core.dao.IEhcAppInfoMapper;
import com.taomini.model.EhcAppInfoDTO;
import com.taomini.service.IEhcAppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author chentao
 * @create 2020-1-8
 * @since 1.0.0
 */
@Service
public class EhcAppInfoServiceImpl implements IEhcAppInfoService {

    @Autowired
    private IEhcAppInfoMapper ehcAppInfoMapper;

    @Override
    public List<EhcAppInfoDTO> getAllEhcInfo() {
        return ehcAppInfoMapper.getAllAppInfo();
    }
}