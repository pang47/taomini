package com.taomini.core.dao;

import com.taomini.model.AccountTransInfoDTO;

public interface IAccountTransInfoMapper {

    void save(AccountTransInfoDTO accountTransInfoDTO);

    void queryLimitTen(AccountTransInfoDTO accountTransInfoDTO);
}
