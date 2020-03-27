package com.taomini.service;

import com.alibaba.fastjson.JSONArray;
import com.taomini.core.common.Result;

public interface ILimitService {
    JSONArray getLimitList();

    Result uploadLimitInfo(String money, String type);
}
