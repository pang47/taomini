package com.taomini.service;


import com.taomini.model.EhcAppInfoDTO;

import java.util.List;

/**
 *
 *
 * @author chentao
 * @create 2020-1-8
 * @since 1.0.0
 */
public interface IEhcAppInfoService {
    List<EhcAppInfoDTO> getAllEhcInfo();
}