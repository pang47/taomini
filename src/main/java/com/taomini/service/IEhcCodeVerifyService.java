package com.taomini.service;

import com.taomini.model.EhcAppInfoDTO;

import java.util.List;

public interface IEhcCodeVerifyService {
    String verifyCode(String code);

    List<EhcAppInfoDTO> getAllInfo();
}
