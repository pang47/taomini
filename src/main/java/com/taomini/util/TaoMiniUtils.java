package com.taomini.util;

import com.taomini.core.constant.AccountChannelEnum;
import com.taomini.core.constant.IniConfigEnum;
import com.taomini.core.constant.TransTypeEnum;
import com.taomini.core.constant.UserConstant;
import com.taomini.model.IniConfigDTO;
import com.taomini.service.IIniConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 工具类
 *
 * @author chentao
 * @create 2019/9/7
 * @since 1.0.0
 */
@Component
public class TaoMiniUtils {

    @Autowired
    IIniConfigService iIniConfigService;
    
    public static String[] imgList = {"https://www.pangt.xyz/breakfast.png",
            "https://www.pangt.xyz/lunch.png",
            "https://www.pangt.xyz/dinner.png",
            "https://www.pangt.xyz/yule.png",
            "https://www.pangt.xyz/jiaoyou.png",
            "https://www.pangt.xyz/fushi.png",
            "https://www.pangt.xyz/bus.png",
            "https://www.pangt.xyz/xigua.png",
            "https://www.pangt.xyz/house.png",
            "https://www.pangt.xyz/qinqi.png",
            "https://www.pangt.xyz/shuma.png",
            "https://www.pangt.xyz/meirong.png",
            "https://www.pangt.xyz/lingshi.png",
            "https://www.pangt.xyz/lvxing.png",
            "https://www.pangt.xyz/jujia.png",
            "https://www.pangt.xyz/other.png",
            "https://www.pangt.xyz/supermarket.png",
            "https://www.pangt.xyz/taobao.png",
            "https://www.pangt.xyz/income.png"};
    
    public String getTransTypeName(String transType){
        IniConfigDTO dto = iIniConfigService.getIniConfig4One(IniConfigEnum.TRANSTYPE.getIniType(), IniConfigEnum.TRANSTYPE.getIniClass(), transType);
        return dto.getIniCodeValue();
    }

    public static String getTransImageUrl(String transType){
        return imgList[Integer.parseInt(transType) - 1];
    }

    public static String getTransActiveImageUrl(String imageUrl){
        int index = imageUrl.lastIndexOf(".");
        return imageUrl.substring(0, index) + "-active." + imageUrl.substring(index+1);
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

    public static String getChannelName(String channel){
        if(AccountChannelEnum.ALICHANNEL.getCode().equals(channel)){
            return AccountChannelEnum.ALICHANNEL.getValue();
        }else if(AccountChannelEnum.HUABEICHANNEL.getCode().equals(channel)){
            return AccountChannelEnum.HUABEICHANNEL.getValue();
        }else if(AccountChannelEnum.ZHAOHANGCHANNEL.getCode().equals(channel)){
            return AccountChannelEnum.ZHAOHANGCHANNEL.getValue();
        }else if(AccountChannelEnum.JIANHANGCHANNEL.getCode().equals(channel)){
            return AccountChannelEnum.JIANHANGCHANNEL.getValue();
        }else if(AccountChannelEnum.GONGHANGCHANNEL.getCode().equals(channel)){
            return AccountChannelEnum.GONGHANGCHANNEL.getValue();
        }else if(AccountChannelEnum.WECHATCHANNEL.getCode().equals(channel)){
            return AccountChannelEnum.WECHATCHANNEL.getValue();
        }else if(AccountChannelEnum.DEBTCHANNEL.getCode().equals(channel)){
            return AccountChannelEnum.DEBTCHANNEL.getValue();
        }else if(AccountChannelEnum.JINGDONGCHANNEL.getCode().equals(channel)){
            return AccountChannelEnum.JINGDONGCHANNEL.getValue();
        }else{
            return "未知";
        }
    }

    public static String getUserName(String openId){
        if(UserConstant.TAO.equals(openId)){
            return UserConstant.TAOUSER.getUserName();
        }else if(UserConstant.SIQI.equals(openId)){
            return UserConstant.SIQIUSER.getUserName();
        }else{
            return "第三者";
        }
    }

    public static void main(String[] args) {
        System.out.println(getTransActiveImageUrl("12"));
    }

}