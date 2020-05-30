package com.taomini.util;


import com.taomini.core.constant.CacheConstant;
import com.taomini.core.ehcache.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
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

    @Scheduled(cron = "0 */3 * * * ?")
    public void doTask(){

        Map map = new HashMap<String, String>();
        map.put("reqData","{\"source\":2,\"sharePin\":\"\",\"userId\":\"81F3D88895BB4C14B5F6080AEF39F96C\",\"userToken\":\"D61478EBF834F303C9F4A819FB138CE8\",\"shareType\":1,\"channel\":\"\",\"riskDeviceParam\":\"{\\\"eid\\\":\\\"25JVJXK3XIZOULWTGUSOTQYCOADGVO63PTK63E3L6LRGVTJED5GIOAA7BRSFZGDFBFG6OCK5GAN5VRI5MBADFZBP6U\\\",\\\"dt\\\":\\\"iPhone 6 (A1549/A1586)\\\",\\\"ma\\\":\\\"\\\",\\\"im\\\":\\\"\\\",\\\"os\\\":\\\"iOS\\\",\\\"osv\\\":\\\"12.2\\\",\\\"ip\\\":\\\"120.36.252.21\\\",\\\"apid\\\":\\\"JDJR-App\\\",\\\"ia\\\":\\\"\\\",\\\"uu\\\":\\\"\\\",\\\"cv\\\":\\\"5.4.30\\\",\\\"nt\\\":\\\"WIFI\\\",\\\"at\\\":\\\"1\\\",\\\"fp\\\":\\\"2701bcbd6d0602c652b396522453244d\\\",\\\"token\\\":\\\"QXX7EAONT4OUZRUPHGP3TUQAKVKM6M5JJOM4OBM5SLVWM4I57MVKGXOFGPIHIIGJRHPRHOCHFGGMC\\\"}\"}");


        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("Accept-Enconding", "gzip, deflate, br");
        header.put("Accept-Language", "zh-CN");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148/application=JDJR-App&deviceId=2DE69896-EC9F-423F-BBDC-0F99A2795E77&clientType=ios&iosType=iphone&clientVersion=5.4.30&HiClVersion=5.4.30&isUpdate=0&osVersion=12.2&osName=iOS&platform=iPhone 6 (A1549/A1586)&screen=667*375&src=App Store&ip=240e:379:aaef:5b00:86a:5c62:b2a7:9f81&mac=02:00:00:00:00:00&netWork=1&netWorkType=1&CpayJS=UnionPay/1.0 JDJR&stockSDK=stocksdk-iphone_3.2.0&sPoint=MTUwMDMjI3Nob3V5ZTYwMDFfeyJtYXRpZCI6IuaQnOe0oiIsImFidGlkIjoiIiwiY2JwIjoiIn0%3D&jdPay=(*#@jdPaySDK*#@jdPayChannel=jdfinance&jdPayChannelVersion=5.4.30&jdPaySdkVersion=2.25.55.00&jdPayClientName=iOS*#@jdPaySDK*#@)");
        header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        header.put("Origin", "https://uua.jr.jd.com");
        header.put("Referer", "https://uua.jr.jd.com/uc-fe-wxgrowing/moneytree/index/?Channel=jhy&showhead=no&babelChannel=guanjianci&sid=5de33ad4af6a955fe00b1cd3bc7cb46w");
        header.put("Connection", "keep-alive");
        header.put("Host", "ms.jr.jd.com");



        //cookie值修改
        String cookies = "qqd_fs=1590805641946; qd_ls=1590805641946; qd_sid=KAT0LT5Q-YALB062U79XM78VCI5Y4-1; qd_sq=1; qd_ts=1590805641946; qd_uid=KAT0LT5Q-YALB062U79XM78VCI5Y4; __jd_ref_cls=Mnpm_ComponentApplied; mba_muid=1590805640235599552075; mba_sid=15908056402413957549985770485.5; 3AB9D23F7A4B3C9B=25JVJXK3XIZOULWTGUSOTQYCOADGVO63PTK63E3L6LRGVTJED5GIOAA7BRSFZGDFBFG6OCK5GAN5VRI5MBADFZBP6U; __jda=122270672.1590805640235599552075.1590805640.1590805640.1590805640.1; __jdb=122270672.5.1590805640235599552075|1.1590805640; __jdc=122270672; qd_ad=-%7C-%7C-%7C-%7C0; __jrr=CFD173DA1AE5E76AB75BA5CB010807; __jdv=122270672%7Cdirect%7C-%7Cnone%7C-%7C1590805640236; pt_key=app_openAAJe0cSHADArxI6bsPqSLmliEfDUhcmbhFmZoe9OReMCKf8LQsm0mMJjJVumfMQ4p0-YWRjb6cs; pt_pin=jd_69469f4ef2092; pwdt_id=jd_69469f4ef2092; sid=5de33ad4af6a955fe00b1cd3bc7cb46w";
        logger.info("---------------------京东定时任务 开始------------------------");
        String ret = HttpUtils.doPost("https://ms.jr.jd.com/gw/generic/uc/h5/m/harvest?_=" + System.currentTimeMillis(), map, header, cookies);
        logger.info("返回信息:{}", ret);
        logger.info("---------------------京东定时任务 结束------------------------");
    }


    @Scheduled(cron = "0 */1 * * * ?")
    public void doTaskD(){

        Map map = new HashMap<String, String>();
        map.put("reqData","{\"source\":2,\"sharePin\":\"\",\"userId\":\"7936EFFFD1F092D54771C1A17C223B35\",\"userToken\":\"5C2AE2A7B2A37681F430805B4F353EC1\",\"shareType\":1,\"channel\":\"\",\"riskDeviceParam\":\"{\\\"eid\\\":\\\"ZHXOJNAOBYJQO7HAPVOPM3MPPFHKEWILTDOLCBCYGZANGEGVUG5BAJF555ALUEIF4ANUSWMYTLAKPDTWAQPSTVTISQ\\\",\\\"dt\\\":\\\"iPhone 6 (A1549/A1586)\\\",\\\"ma\\\":\\\"\\\",\\\"im\\\":\\\"\\\",\\\"os\\\":\\\"iOS\\\",\\\"osv\\\":\\\"12.2\\\",\\\"ip\\\":\\\"120.36.252.21\\\",\\\"apid\\\":\\\"JDJR-App\\\",\\\"ia\\\":\\\"\\\",\\\"uu\\\":\\\"\\\",\\\"cv\\\":\\\"5.4.30\\\",\\\"nt\\\":\\\"WIFI\\\",\\\"at\\\":\\\"1\\\",\\\"fp\\\":\\\"5c99718730c8ad7c229480927e9639ac\\\",\\\"token\\\":\\\"KNGM3HGKYT4U7SNNUOXUMMNMZ3EIGT7VCHZM6HGPK65VSPBWWWRA3S6S3OFHPRUMQR2KCSAVHK57K\\\"}\"}");


        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("Accept-Enconding", "gzip, deflate, br");
        header.put("Accept-Language", "zh-CN");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148/application=JDJR-App&deviceId=2DE69896-EC9F-423F-BBDC-0F99A2795E77&clientType=ios&iosType=iphone&clientVersion=5.4.30&HiClVersion=5.4.30&isUpdate=0&osVersion=12.2&osName=iOS&platform=iPhone 6 (A1549/A1586)&screen=667*375&src=App Store&ip=240e:379:aaef:5b00:86a:5c62:b2a7:9f81&mac=02:00:00:00:00:00&netWork=1&netWorkType=1&CpayJS=UnionPay/1.0 JDJR&stockSDK=stocksdk-iphone_3.2.0&sPoint=MTUwMDMjI3Nob3V5ZTYwMDFfeyJtYXRpZCI6IuaQnOe0oiIsImFidGlkIjoiIiwiY2JwIjoiIn0%3D&jdPay=(*#@jdPaySDK*#@jdPayChannel=jdfinance&jdPayChannelVersion=5.4.30&jdPaySdkVersion=2.25.55.00&jdPayClientName=iOS*#@jdPaySDK*#@)");
        header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        header.put("Origin", "https://uua.jr.jd.com");
        header.put("Referer", "https://uua.jr.jd.com/uc-fe-wxgrowing/moneytree/index/?channel=gry&jrcontainer=h5&jrlogin=true");
        header.put("Connection", "keep-alive");
        header.put("Host", "ms.jr.jd.com");



        //cookie值修改
        String cookies = "qd_fs=1590808613833; qd_ls=1590808613833; qd_sid=KAT2DIA3-BNV78K0Q7TQI3G08FE1S-1; qd_sq=1; qd_ts=1590808613833; qd_uid=KAT2DIA3-BNV78K0Q7TQI3G08FE1S; __jd_ref_cls=yqs2018%7Ctixian; mba_muid=15908086145971044412013; mba_sid=15908086146038475878552449192.1; 3AB9D23F7A4B3C9B=ZHXOJNAOBYJQO7HAPVOPM3MPPFHKEWILTDOLCBCYGZANGEGVUG5BAJF555ALUEIF4ANUSWMYTLAKPDTWAQPSTVTISQ; __jda=122270672.15908086145971044412013.1590808614.1590808614.1590808614.1; __jdb=122270672.1.15908086145971044412013|1.1590808614; __jdc=122270672; __jdv=122270672%7Cdirect%7C-%7Cnone%7C-%7C1590808614598; __jrr=80C14912E87A9346592FDFD934790E; qd_ad=-%7C-%7Cdirect%7C-%7C0; pt_key=app_openAAJe0dAkADCkR1fe53nGfVGLXSAB0gZ9dtVG9ptpBbJiAY-ChMAtuZBlqGYWxZFHYxgy2eOpIe8; pt_pin=jd_648a9d58e25ba; pwdt_id=jd_648a9d58e25ba; sid=5a5fb7dc1a0a99469469628c24854dew";
        logger.info("---------------------京东定时任务D 开始------------------------");
        String ret = HttpUtils.doPost("https://ms.jr.jd.com/gw/generic/uc/h5/m/harvest?_=" + System.currentTimeMillis(), map, header, cookies);
        logger.info("返回信息:{}", ret);
        logger.info("---------------------京东定时任务D 结束------------------------");
    }

    @Scheduled(cron = "0 */3 * * * ?")
    public void doTaskSQ(){

        Map map = new HashMap<String, String>();
        map.put("reqData","{\"source\":2,\"sharePin\":\"\",\"userId\":\"6557655749932E4706875ED563505D65\",\"userToken\":\"63E53B5642B4B12957565C659B51FA34\",\"shareType\":1,\"channel\":\"\",\"riskDeviceParam\":\"{\\\"eid\\\":\\\"AR4HCSI2K5USZOP2M5ICRF5X5AFIKCF6KMMEYMOIWSXVDKFFI4KWYRYAHITXG3Q5TJ27BBW5ADLIXSPO2V7KZGWOKE\\\",\\\"dt\\\":\\\"iPhone 6 (A1549/A1586)\\\",\\\"ma\\\":\\\"\\\",\\\"im\\\":\\\"\\\",\\\"os\\\":\\\"iOS\\\",\\\"osv\\\":\\\"12.2\\\",\\\"ip\\\":\\\"120.36.252.21\\\",\\\"apid\\\":\\\"JDJR-App\\\",\\\"ia\\\":\\\"\\\",\\\"uu\\\":\\\"\\\",\\\"cv\\\":\\\"5.4.30\\\",\\\"nt\\\":\\\"WIFI\\\",\\\"at\\\":\\\"1\\\",\\\"fp\\\":\\\"5c99718730c8ad7c229480927e9639ac\\\",\\\"token\\\":\\\"VRHONNQC5H6S5U6KKSSWXFBCYCOUYTPK6B23LB3AKTUTS3ULO6KMCQUGEDXFIKYWHZ3TVN77OVLFK\\\"}\"}");


        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("Accept-Enconding", "gzip, deflate, br");
        header.put("Accept-Language", "zh-CN");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148/application=JDJR-App&deviceId=2DE69896-EC9F-423F-BBDC-0F99A2795E77&clientType=ios&iosType=iphone&clientVersion=5.4.30&HiClVersion=5.4.30&isUpdate=0&osVersion=12.2&osName=iOS&platform=iPhone 6 (A1549/A1586)&screen=667*375&src=App Store&ip=240e:379:aaef:5b00:86a:5c62:b2a7:9f81&mac=02:00:00:00:00:00&netWork=1&netWorkType=1&CpayJS=UnionPay/1.0 JDJR&stockSDK=stocksdk-iphone_3.2.0&sPoint=MTUwMDMjI3Nob3V5ZTYwMDFfeyJtYXRpZCI6IuaQnOe0oiIsImFidGlkIjoiIiwiY2JwIjoiIn0%3D&jdPay=(*#@jdPaySDK*#@jdPayChannel=jdfinance&jdPayChannelVersion=5.4.30&jdPaySdkVersion=2.25.55.00&jdPayClientName=iOS*#@jdPaySDK*#@)");
        header.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        header.put("Origin", "https://uua.jr.jd.com");
        header.put("Referer", "https://uua.jr.jd.com/uc-fe-wxgrowing/moneytree/index/?channel=gry&jrcontainer=h5&jrlogin=true");
        header.put("Connection", "keep-alive");
        header.put("Host", "ms.jr.jd.com");



        //cookie值修改
        String cookies = "qd_fs=1590808200836; qd_ls=1590808200836; qd_sid=KAT24NLY-G7ZJDW9IFNG43CZBWAVH-1; qd_sq=1; qd_ts=1590808200836; qd_uid=KAT24NLY-G7ZJDW9IFNG43CZBWAVH; __jd_ref_cls=Mnpm_ComponentApplied; mba_muid=15908082016831287150644; mba_sid=15908082016923473341678036925.2; __jrr=2F8166FC0FEF0DD1C27A97AA08A1DE; 3AB9D23F7A4B3C9B=AR4HCSI2K5USZOP2M5ICRF5X5AFIKCF6KMMEYMOIWSXVDKFFI4KWYRYAHITXG3Q5TJ27BBW5ADLIXSPO2V7KZGWOKE; __jda=122270672.15908082016831287150644.1590808201.1590808201.1590808201.1; __jdb=122270672.2.15908082016831287150644|1.1590808201; __jdc=122270672; qd_ad=-%7C-%7Cdirect%7C-%7C0; __jdv=122270672%7Cdirect%7C-%7Cnone%7C-%7C1590808201684; pt_key=app_openAAJe0c6HADBniiMwFgqaTSAd3iQRpIq_zP2N1fQhTSXlEIwYPXPd_-jMSMYdHxN2ehB82jCDVZc; pt_pin=36434322-125051; pwdt_id=36434322-125051; sid=494a2246a3aa23d5f5c402541f91644w";
        logger.info("---------------------京东定时任务SQ 开始------------------------");
        String ret = HttpUtils.doPost("https://ms.jr.jd.com/gw/generic/uc/h5/m/harvest?_=" + System.currentTimeMillis(), map, header, cookies);
        logger.info("返回信息:{}", ret);
        logger.info("---------------------京东定时任务SQ 结束------------------------");
    }

    public static void main(String[] args) {
        ScheduleTask tast = new ScheduleTask();
        tast.doTaskD();
    }

}