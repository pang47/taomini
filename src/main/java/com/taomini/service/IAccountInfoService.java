package com.taomini.service;

import com.taomini.core.common.TaoMiniException;
import com.taomini.model.AccountInfoDTO;
import com.taomini.model.AccountTransInfoDTO;
import com.taomini.model.vo.AccountInfoVo;

import java.util.List;

public interface IAccountInfoService {
    List<AccountInfoVo> getAccountInfo();

    void saveOrUpdateAccountInfo(AccountInfoDTO accountInfoDTO);

    int updateAccountInfo();

    List<AccountTransInfoDTO> getAccountTransInfo(String length);

    String[] getAccountInfoByChannel(String channel) throws TaoMiniException;
}
