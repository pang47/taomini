package com.taomini.util;

import com.taomini.core.constant.AccountChannelEnum;
import com.taomini.core.constant.TransTypeEnum;

/**
 * 工具类
 *
 * @author chentao
 * @create 2019/9/7
 * @since 1.0.0
 */
public class TaoMiniUtils {
    
    public static String[] imgList = {"../../image/breakfast.png",
            "../../image/lunch.png",
            "../../image/dinner.png",
            "../../image/yule.png",
            "../../image/jiaoyou.png",
            "../../image/fushi.png",
            "../../image/bus.png",
            "../../image/xigua.png",
            "../../image/house.png",
            "../../image/qinqi.png",
            "../../image/shuma.png",
            "../../image/meirong.png",
            "../../image/lingshi.png",
            "../../image/lvxing.png",
            "../../image/jujia.png",
            "../../image/other.png",
            "../../image/other.png",
            "../../image/other.png"};
    
    public static String getTransTypeName(String transType){
        String retMsg = "无对应交易类型";
        if(TransTypeEnum.BREAKFASTTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.BREAKFASTTRANS.getTransTypeName();
        }else if(TransTypeEnum.LUNCHTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.LUNCHTRANS.getTransTypeName();
        }else if(TransTypeEnum.DINNERTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.DINNERTRANS.getTransTypeName();
        }else if(TransTypeEnum.HAPPYTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.HAPPYTRANS.getTransTypeName();
        }else if(TransTypeEnum.FRIENDSTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.FRIENDSTRANS.getTransTypeName();
        }else if(TransTypeEnum.CLOTHERTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.CLOTHERTRANS.getTransTypeName();
        }else if(TransTypeEnum.BUSTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.BUSTRANS.getTransTypeName();
        }else if(TransTypeEnum.XIGUATRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.XIGUATRANS.getTransTypeName();
        }else if(TransTypeEnum.HOUSETRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.HOUSETRANS.getTransTypeName();
        }else if(TransTypeEnum.QINQITRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.QINQITRANS.getTransTypeName();
        }else if(TransTypeEnum.SHUMATRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.SHUMATRANS.getTransTypeName();
        }else if(TransTypeEnum.MEIRONGTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.MEIRONGTRANS.getTransTypeName();
        }else if(TransTypeEnum.LINGSHITRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.LINGSHITRANS.getTransTypeName();
        }else if(TransTypeEnum.LVXINGTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.LVXINGTRANS.getTransTypeName();
        }else if(TransTypeEnum.JUJIATRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.JUJIATRANS.getTransTypeName();
        }else if(TransTypeEnum.OHTERTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.OHTERTRANS.getTransTypeName();
        }else if(TransTypeEnum.TAOBAOTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.TAOBAOTRANS.getTransTypeName();
        }else if(TransTypeEnum.SUPERMARKETTRANS.getTransType().equals(transType)){
            retMsg = TransTypeEnum.SUPERMARKETTRANS.getTransTypeName();
        }
        return retMsg;
    }

    public static String getTransImageUrl(String transType){
        return imgList[Integer.parseInt(transType) - 1];
    }

    public static String getChannel(String channelName){
        if(AccountChannelEnum.ALICHANNEL.getValue().equals(channelName)){
            return AccountChannelEnum.ALICHANNEL.getCode();
        }else if(AccountChannelEnum.HUABEICHANNEL.getValue().equals(channelName)){
            return AccountChannelEnum.HUABEICHANNEL.getCode();
        }else if(AccountChannelEnum.ZHAOHANGCHANNEL.getValue().equals(channelName)){
            return AccountChannelEnum.ZHAOHANGCHANNEL.getCode();
        }else if(AccountChannelEnum.JIANHANGCHANNEL.getValue().equals(channelName)){
            return AccountChannelEnum.JIANHANGCHANNEL.getCode();
        }else if(AccountChannelEnum.GONGHANGCHANNEL.getValue().equals(channelName)){
            return AccountChannelEnum.GONGHANGCHANNEL.getCode();
        }else if(AccountChannelEnum.WECHATCHANNEL.getValue().equals(channelName)){
            return AccountChannelEnum.WECHATCHANNEL.getCode();
        }else if(AccountChannelEnum.DEBTCHANNEL.getValue().equals(channelName)){
            return AccountChannelEnum.DEBTCHANNEL.getCode();
        }else if(AccountChannelEnum.JINGDONGCHANNEL.getValue().equals(channelName)){
            return AccountChannelEnum.JINGDONGCHANNEL.getCode();
        }else{
            return "未知";
        }
    }

}