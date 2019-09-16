package com.taomini.core.dao;

import com.taomini.model.AccountTransInfoDTO;

import java.util.List;

public interface IAccountTransInfoMapper {

    void save(AccountTransInfoDTO accountTransInfoDTO);

    List<AccountTransInfoDTO> queryLimit(Integer length);

    List<AccountTransInfoDTO> queryByChannel(String channel);
}
