package com.taomini.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.taomini.core.constant.TransTypeEnum;
import com.taomini.core.constant.UserConstant;
import com.taomini.core.dao.IFormInfoMapper;
import com.taomini.model.FormInfoDTO;
import com.taomini.model.TransRecordDTO;
import com.taomini.service.IPushMessageService;
import com.taomini.service.ITransRecordService;
import com.taomini.third.wx.WxApiUtils;
import com.taomini.util.DateUtil;
import com.taomini.util.TaoMiniUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author chentao
 * @create 2019/9/3
 * @since 1.0.0
 */
@Service
public class PushMessageServiceImpl implements IPushMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushMessageServiceImpl.class);

    @Autowired
    IFormInfoMapper formInfoMapper;

    @Autowired
    ITransRecordService transRecordService;

    @Override
    public void saveFormInfo(FormInfoDTO dto) {
        formInfoMapper.saveFormInfo(dto);
    }

    /**
     * 推送消息
     */
    @Override
    public void pushMessage(String tempeleId) {
        JSONObject pushMessage = new JSONObject();

        //拼装消息
        JSONObject data = new JSONObject();
        JSONObject time = new JSONObject();
        time.put("value", DateUtil.formatDate(DateUtil.getCurrDateTime()));
        data.put("date12", time);

        JSONObject amount = new JSONObject();
        amount.put("value", transRecordService.getTransMoneyDate());
        data.put("amount10", amount);

        JSONObject ting = new JSONObject();
        ting.put("value", transRecordService.getTransReportDate());
        data.put("thing9", ting);

        pushMessage.put("touser", UserConstant.TAO);
        pushMessage.put("template_id", tempeleId);
        pushMessage.put("data", data);

        WxApiUtils.pushMessage(pushMessage);

    }

    @Override
    public void pushMessage(String[] data, String templeteId) {
        FormInfoDTO taoForm = formInfoMapper.getFormInfoByOpenId(UserConstant.TAO);
        if (taoForm == null) {
            LOGGER.info("推送信息不足,TAO");
        }else{
            taoForm.setPushDate(DateUtil.getCurrDate());
            taoForm.setPushTime(DateUtil.getCurrTime());
            taoForm.setPushMsg(getSendMsg(data).toJSONString());
            taoForm.setTemplete(templeteId);
            taoForm = WxApiUtils.pushMessage(taoForm);
            formInfoMapper.updateFormInfo(taoForm);
        }

        FormInfoDTO SQForm = formInfoMapper.getFormInfoByOpenId(UserConstant.SIQI);
        if (SQForm == null) {
            LOGGER.info("推送信息不足,SIQI");
        } else {
            SQForm.setPushDate(DateUtil.getCurrDate());
            SQForm.setPushTime(DateUtil.getCurrTime());
            SQForm.setPushMsg(getSendMsg(data).toJSONString());
            SQForm.setTemplete(templeteId);
            SQForm = WxApiUtils.pushMessage(SQForm);
            formInfoMapper.updateFormInfo(SQForm);
        }
    }

    private JSONObject getSendMsg(String[] strs){
        JSONObject data = new JSONObject();
        for (int i = 0; i < strs.length; i++) {
            JSONObject value = new JSONObject();
            value.put("value", strs[i]);
            data.put("keyword" + (i + 1), value);
        }
        return data;
    }

}