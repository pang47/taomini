package com.taomini.controller;

import com.taomini.core.common.Result;
import com.taomini.core.socket.EhcCodeSocketServer;
import com.taomini.service.IEhcCodeVerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 二维码接受请求
 *
 * @author chentao
 * @create 2020-1-8
 * @since 1.0.0
 */
@RequestMapping("/ehc")
@RestController
public class EhcCodeController {
    private static final Logger logger = LoggerFactory.getLogger(EhcCodeSocketServer.class);

    @Autowired
    private IEhcCodeVerifyService ehcCodeVerifyService;

    @RequestMapping(value="codeVerify")
    @ResponseBody
    public Result ehcCodeVerify(String code){
        Result result = new Result();

        try{
            String retStr = ehcCodeVerifyService.verifyCode(code);
            if(retStr.equals("0000")){
                //发送成功
                result.setSucc(true);
            }else{
                //发送失败
                result.setSucc(false);
                result.setRetMsg("发送失败，检查服务");
            }
            logger.info("返回信息:{}", retStr);
        }catch (Exception e){
            logger.error("发送失败，检查服务", e);
            result.setSucc(false);
            result.setRetMsg("发送失败，检查服务");
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value="getAllEhcInfo")
    public Result getAllEhcInfo(){
        Result result = new Result();

        result.setSucc(true);
        result.setData(ehcCodeVerifyService.getAllInfo());

        return result;
    }
}