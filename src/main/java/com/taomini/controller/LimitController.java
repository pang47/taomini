package com.taomini.controller;

import com.taomini.core.common.Result;
import com.taomini.service.ILimitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/limit")
public class LimitController {

    private static final Logger logger = LoggerFactory.getLogger(LimitController.class);

    @Autowired
    ILimitService limitService;

    @RequestMapping(value = "/getLimitList")
    @ResponseBody
    public Result getLimitList(){
        Result result = new Result();

        result.setSucc(true);

        result.setData(limitService.getLimitList());

        return result;
    }

    @RequestMapping(value = "/uploadLimitInfo")
    @ResponseBody
    public Result uploadLimitInfo(String money, String type){
        Result result = new Result();
        try{
            result.setSucc(true);

            result.setData(limitService.uploadLimitInfo(money, type));
        }catch (Exception e){
            result.setSucc(false);
            logger.error("上传额度失败，", e);
            result.setRetMsg("上传额度失败");

        }

        return result;
    }

}
