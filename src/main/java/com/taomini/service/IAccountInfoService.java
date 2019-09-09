package com.taomini.service;

import com.taomini.model.AccountInfoDTO;

import java.util.List;

public interface IAccountInfoService {
    List<AccountInfoDTO> getAccountInfo();

    void saveOrUpdateAccountInfo(AccountInfoDTO accountInfoDTO);

    int updateAccountInfo();
}
