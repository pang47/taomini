package com.taomini.util;


import com.taomini.core.constant.CacheConstant;
import com.taomini.core.ehcache.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 定时任务
 * 〈〉
 *
 * @author chentao
 * @create 2019/5/31
 * @since 1.0.0
 */
@Component
public class ScheduleTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

//    @Scheduled(cron = "0 */5 * * * ?")
    public void doTask(){
        Map map = new HashMap<String, String>();
        map.put("reqData","{\"source\":0,\"sharePin\":\"7shPo28N444IB69KXKfJCA\"}");

        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        //header.put("Content-Length", "78");
        header.put("Origin", "https://u.jr.jd.com");
        header.put("Referer", "https://u.jr.jd.com/wxgrowing/moneytree/index.html?channelLV=&shareType=1&sharePin=7shPo28N444IB69KXKfJCA&utm_source=iOS*url*1559263992494&utm_medium=wechatshare&utm_campaign=cFNOux1KhoOuSu0GcCg0rg==**11&utm_term=&from=singlemessage&isappinstalled=0");
        header.put("User-Agent", "MICROMESSENGER");
        header.put("Connection", "keep-alive");
        //header.put("Host", "ms.jr.jd.com");
        header.put("Cache-Control", "no-cache");
        header.put("Pragma", "no-cache");
        header.put("Accept-Enconding", "gzip, deflate, br");
        header.put("Accept-Language", "zh-CN,zh;q=0.9");

        //cookie值修改
        String cookies = "__jdu=412293007; areaId=16; ipLoc-djd=16-1315-3486-0; shshshfpb=0985d61a006a84ff299e7f9260ed5e1cabe755024af4e14295b7ab312e; shshshfpa=5e1c5952-b355-ea0d-fcb4-8906285ee0fb-1558803281; unpl=V2_ZzNtbUJWFBN8WkNVekkOUGICEQ9LAhMUcV9OVH4bXA1lV0YPclRCFX0URldnGF8UZwcZXUBcQx1FCEdkeBBVAWMDE1VGZxBFLV0CFSNGF1wjU00zQwBBQHcJFF0uSgwDYgcaDhFTQEJ2XBVQL0oMDDdRFAhyZ0AVRQhHZH4ZXwZvARNcRGdzEkU4dlF7Gl4CZTMTbUNnAUEpAEFTfRxfSGIDEV5KVUIUczhHZHg%3d; __jdv=76161171|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_10f78c400acd402c8da05f9143193eec|1575270800689; __jda=122270672.412293007.1574994967.1574994971.1575270801.2; user-key=95250f6e-e4f0-4688-a3ba-3bc521e0346f; cn=0; shshshfp=5783bc63f8a1d76d965877f47677d0bc; qd_ad=iOS*url*1574642604831%7C-%7Cjrappshare%7Cqqfriends%7C10; qd_uid=K3SALLH1-SFBJCR9DK2GN4MWACQ8O; qd_fs=1575524485617; qd_ls=1575524485617; qd_ts=1575524485617; qd_sq=1; qd_sid=K3SALLH1-SFBJCR9DK2GN4MWACQ8O-1; __jrr=41160065942945977E0187C384A1FF; TrackerID=eQm3rSnLZYPTQI8xfQ2GJZDL-fA4wCXn3PFdikpm44v4i4AEiTpqzRCIPx03V4RLLC_32D1D_d489IaUi-Vh67A4WWmvqLrC_JICijRaH-wp9fcC9M9t_JH-42FCDeScKFzi-Xm9aURR6fjbZlUWrA; pt_key=AAJd6J-TADB4_LN9aWNXeIja-qsqhclnln0-CvZ6rB7gFx7XP8sv-KF71FYlltc2E8GBWGumdok; pt_pin=jd_69469f4ef2092; pt_token=5ny1pmw5; pwdt_id=jd_69469f4ef2092; 3AB9D23F7A4B3C9B=5REP2RNPWYD3TGSKEBJUG7P2A34T2SD65UPLDL6ZNU7DBMCYX5AYLHXEH22MV4GI5CYHVRHHL5GUSXD3MHTMAOGH2M";
        logger.info("---------------------京东定时任务 开始------------------------");
        String ret = HttpUtils.doPost("https://ms.jr.jd.com/gw/generic/uc/h5/m/harvest?_=" + System.currentTimeMillis(), map, header, cookies);
        logger.info("返回信息:{}", ret);
        logger.info("---------------------京东定时任务 结束------------------------");
    }

    public static void main(String[] args) {
        ScheduleTask tast = new ScheduleTask();
        tast.doTask();
    }

}