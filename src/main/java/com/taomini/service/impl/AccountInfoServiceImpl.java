package com.taomini.service.impl;


import com.taomini.core.constant.UserConstant;
import com.taomini.core.dao.IAccountInfoMapper;
import com.taomini.model.AccountInfoDTO;
import com.taomini.service.IAccountInfoService;
import com.taomini.util.DateUtil;
import com.taomini.util.TaoMiniUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author chentao
 * @create 2019/9/9
 * @since 1.0.0
 */
@Service
public class AccountInfoServiceImpl implements IAccountInfoService {

    @Autowired
    IAccountInfoMapper accountInfoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

    @Override
    public List<AccountInfoDTO> getAccountInfo() {
        return null;
    }

    @Override
    public void saveOrUpdateAccountInfo(AccountInfoDTO accountInfoDTO) {
        String channel = TaoMiniUtils.getChannel(accountInfoDTO.getChannelName());
        String user = UserConstant.TAOUSER.getUserName().equals(accountInfoDTO.getUserName())?UserConstant.TAOUSER.getOpenId():UserConstant.SIQIUSER.getOpenId();

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
            accountInfoMapper.saveAccount(accountInfoDTO);
        }else{
            //更新
            accountInfoDTO.setUpdateDate(DateUtil.getCurrDate());
            accountInfoDTO.setUpdateTime(DateUtil.getCurrTime());
            accountInfoDTO.setChannel(channel);
            accountInfoDTO.setUser(user);
            accountInfoMapper.updateAccount(accountInfoDTO);
        }
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
}