package com.taomini.service;

import com.taomini.model.FormInfoDTO;

/**
 *
 * @author chentao
 * @create 2019/9/3
 * @since 1.0.0
 */
public interface IPushMessageService {
    void saveFormInfo(FormInfoDTO dto);

    void pushMessage(String templeteId);

    void pushMessageWeek(String templeteId);
}