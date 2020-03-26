package com.taomini.controller;

import com.taomini.core.common.Result;
import com.taomini.service.ILimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/limit")
public class LimitController {

    @Autowired
    ILimitService limitService;

    public Result getLimitList(){
        Result result = new Result();

        result.setSucc(true);

        //result.setData(transRecordService.getTransList());

        return result;
    }

}
