package com.taomini.core.dao;


import com.taomini.model.EhcAppInfoDTO;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author chentao
 * @create 2020-1-8
 * @since 1.0.0
 */
public interface IEhcAppInfoMapper {
    List<EhcAppInfoDTO> getAllAppInfo();
}