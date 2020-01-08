import com.alibaba.fastjson.JSON;
import com.taomini.core.constant.UserConstant;
import com.taomini.core.schedule.SendSchedule;
import com.taomini.service.IIniConfigService;
import com.taomini.service.ITransRecordService;
import com.taomini.util.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author chentao
 * @create 2019/9/11
 * @since 1.0.0
 */

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:spring/spring-all.xml"})
public class TaoSDKTest {

    @Autowired
    SendSchedule sendSchedule;

    @Autowired
    ITransRecordService transRecordService;

    @Autowired
    IIniConfigService iIniConfigService;

    @Test
    public void getPayMonth(){
        Map<String, Object> param = new HashMap<>();
        param.put("transDate", "201909");
        param.put("openId", "123");
        long begin = System.currentTimeMillis();
        System.out.println(HttpUtils.doPost("https://www.pangt.xyz/taomini/record/getTransRecordByUser", param));
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
    }

    @Test
    public void testSpilt(){
        String str1 = "http://m.haobqg.com/html/4_4068/1740875.html";
        String str2 = "http://m.baobqg.com/html/4_4068/1740877.html";
        for (int i=0;i<str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)){
                System.out.println(str1.charAt(i) + "," +  i);
            }
        }

    }

    @Test
    public void testSchedule(){
        sendSchedule.sendMonth();
    }

    @Test
    public void testIncom(){
        long begin = System.currentTimeMillis();
        transRecordService.getTransList();
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

    @Test
    public void testSocket(){
        Map<String, Object> param = new HashMap<>();
        param.put("code", "201909");
        long begin = System.currentTimeMillis();
        System.out.println(HttpUtils.doPost("https://www.pangt.xyz/taomini/ehc/codeVerify", param));
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
    }


    @Test
    public void getAllInfo(){
        Map<String, Object> param = new HashMap<>();
        long begin = System.currentTimeMillis();
        System.out.println(HttpUtils.doPost("http://127.0.0.1:8077/taomini/ehc/getAllEhcInfo", param));
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
    }

}