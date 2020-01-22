package com.taomini.service;

import com.alibaba.fastjson.JSONArray;
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

    void deleteTransRecord(String transId);

    void updateTransRecord(TransRecordDTO dto);

    String[] getTransReportWeek();

    String getTransReportDate();

    String[] getTransReportMonth();

    String getIncomeByMonth(String month);

    JSONArray getTransList();

    List<TransRecordVO> getRecordByDate(String transDate);

    String getTransMoneyDate();
}
