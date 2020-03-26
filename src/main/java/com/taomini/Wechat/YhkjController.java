package com.taomini.Wechat;


import com.alibaba.fastjson.JSONObject;
import com.taomini.util.AESUtil;
import com.taomini.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author chentao
 * @create 2020-2-12
 * @since 1.0.0
 */
@Controller
@RequestMapping(value="yhkj")
public class YhkjController {
    private static final Logger logger = LoggerFactory.getLogger(YhkjController.class);

    private static final String AESKEY = "kmcnkle";

    private static final String AUTHURL = "https://www.pangt.xyz/taomini_dev/";

    @RequestMapping("showOpenId")
    public void showOpenId(HttpServletRequest request){
        String aesData = request.getParameter("aesdata");
        logger.info("返回信息:{}", aesData);

        try {
            JSONObject result = AESUtil.getWXBackResult(aesData);
            logger.info("解密信息:{}", result);
        } catch (Exception e) {
            logger.error("解密信息异常！", e);
        }
    }

    public static void main(String[] args) {
        String accountId = "gh_bd4ed267b4ab";
        String url = "https://www.pangt.xyz/taomini_dev/showOpenId.do/";
        String param = "{'accountid':'" + accountId +"','url':'"+ url +"'}";
        try {
            String data = AESUtil.encrypt(param, AESKEY);
            String p = Base64.encode(data.getBytes("UTF-8"));
            System.out.println(AUTHURL + "?p=" + p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}