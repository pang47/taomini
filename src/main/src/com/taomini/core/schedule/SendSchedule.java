package com.taomini.core.schedule;

import com.taomini.core.constant.TaoMiniConstant;
import com.taomini.service.IPushMessageService;
import com.taomini.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时发送任务
 *
 * @author chentao
 * @create 2019/9/6
 * @since 1.0.0
 */
@Component
public class SendSchedule {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendSchedule.class);

    @Autowired
    IPushMessageService pushMessageService;

    /**
     * 每晚23点发送
     *
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void sendDailyMsg(){
        LOGGER.info("开始发送定时交易信息,{},{}", DateUtil.getCurrDate(), DateUtil.getCurrTime());

        try{
            pushMessageService.pushMessage(TaoMiniConstant.TEMPLETEIDTRANS);
        }catch (Exception e){
            LOGGER.error("发送失败:{}", e.getMessage());
            LOGGER.error("失败", e);
        }
        LOGGER.info("发送成功");

    }

}