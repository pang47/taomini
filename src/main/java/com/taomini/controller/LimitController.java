package com.taomini.controller;

import com.taomini.core.common.Result;
import com.taomini.service.ILimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/limit")
public class LimitController {

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

}
