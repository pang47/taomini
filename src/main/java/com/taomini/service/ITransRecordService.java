package com.taomini.service;

import com.taomini.model.TransRecordDTO;
import com.taomini.model.vo.TransRecordVO;

import java.util.List;

public interface ITransRecordService {
    void saveRecord(TransRecordDTO dto);

    List<TransRecordDTO> getRecordByUserAndDate(String openId, String currDate);

    List<TransRecordVO> getRecordByUser(String openId);

    List<TransRecordVO> getRecordByUserAndDatePage(String openId, String transDate);

    String getPayByMonth(String month);

    TransRecordDTO getTransRecord(String transId);
}
