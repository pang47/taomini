package com.taomini.service.impl;


import com.taomini.core.constant.UserConstant;
import com.taomini.core.dao.IAccountInfoMapper;
import com.taomini.core.dao.IAccountTransInfoMapper;
import com.taomini.model.AccountInfoDTO;
import com.taomini.model.AccountTransInfoDTO;
import com.taomini.service.IAccountInfoService;
import com.taomini.util.DateUtil;
import com.taomini.util.TaoMiniUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author chentao
 * @create 2019/9/9
 * @since 1.0.0
 */
@Service
@Transactional
public class AccountInfoServiceImpl implements IAccountInfoService {

    @Autowired
    IAccountInfoMapper accountInfoMapper;

    @Autowired
    IAccountTransInfoMapper accountTransInfoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

    @Override
    public List<AccountInfoDTO> getAccountInfo() {
        return null;
    }

    @Override
    public void saveOrUpdateAccountInfo(AccountInfoDTO accountInfoDTO) {

        String channel = TaoMiniUtils.getChannel(accountInfoDTO.getChannelName());
        String user = UserConstant.TAOUSER.getUserName().equals(accountInfoDTO.getUserName())?UserConstant.TAOUSER.getOpenId():UserConstant.SIQIUSER.getOpenId();
        String money = "0";
        AccountInfoDTO queryAccount = getAccountInfo(user, channel);
        LOGGER.info("是否存在账户信息:{}", queryAccount != null);
        if(queryAccount == null){
            //插入
            accountInfoDTO.setChannel(channel);
            accountInfoDTO.setUser(user);
            accountInfoDTO.setCrtDate(DateUtil.getCurrDate());
            accountInfoDTO.setCrtTime(DateUtil.getCurrTime());
            accountInfoDTO.setUpdateDate(DateUtil.getCurrDate());
            accountInfoDTO.setUpdateTime(DateUtil.getCurrTime());
            accountInfoDTO.setAccountId(UUID.randomUUID().toString());
            accountInfoMapper.saveAccount(accountInfoDTO);
        }else{
            //更新
            money = queryAccount.getBalance();
            queryAccount.setUpdateDate(DateUtil.getCurrDate());
            queryAccount.setUpdateTime(DateUtil.getCurrTime());
            queryAccount.setBalance(accountInfoDTO.getBalance());
            accountInfoMapper.updateAccount(queryAccount);

            accountInfoDTO = queryAccount;
        }

        addAccountTransInfo(accountInfoDTO, money);

    }

    @Override
    public int updateAccountInfo() {
        return 0;
    }

    private AccountInfoDTO getAccountInfo(String open_id, String channel){
        AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
        accountInfoDTO.setUser(open_id);
        accountInfoDTO.setChannel(channel);
        return accountInfoMapper.getAccountInfoByUserIdAndChannel(accountInfoDTO);
    }

    private void addAccountTransInfo(AccountInfoDTO accountInfoDTO, String oldBanlance){

        int i = 1/0;
        AccountTransInfoDTO accountTransInfoDTO = new AccountTransInfoDTO();
        accountTransInfoDTO.setAccountTransId(UUID.randomUUID().toString());
        accountTransInfoDTO.setChannel(accountInfoDTO.getChannel());
        accountTransInfoDTO.setChannelName(accountInfoDTO.getChannelName());
        accountTransInfoDTO.setCrtDate(DateUtil.getCurrDate());
        accountTransInfoDTO.setCrtTime(DateUtil.getCurrTime());
        accountTransInfoDTO.setUser(accountInfoDTO.getUser());
        accountTransInfoDTO.setMoney(accountInfoDTO.getBalance());
        accountTransInfoDTO.setBeforeBalance(oldBanlance);
        accountTransInfoMapper.save(accountTransInfoDTO);
    }
}