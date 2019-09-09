package com.taomini.core.dao;

import com.taomini.model.FormInfoDTO;

/**
 *
 * @author chentao
 * @create 2019/9/3
 * @since 1.0.0
 */
public interface IFormInfoMapper {
    void saveFormInfo(FormInfoDTO dto);

    FormInfoDTO getFormInfoByOpenId(String openId);

    int updateFormInfo(FormInfoDTO dto);
}