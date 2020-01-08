package com.taomini.service.impl;

import com.taomini.model.EhcAppInfoDTO;
import com.taomini.service.IEhcAppInfoService;
import com.taomini.service.IEhcCodeVerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author chentao
 * @create 2020-1-8
 * @since 1.0.0
 */
@Service
public class EhcCodeVerifyServiceImpl implements IEhcCodeVerifyService {

    private static final Logger logger = LoggerFactory.getLogger(EhcCodeVerifyServiceImpl.class);
    private static final String address = "127.0.0.1";
    private static final int port = 8999;

    @Autowired
    private IEhcAppInfoService ehcAppInfoService;

    @Override
    public String verifyCode(String code) {

        try(Socket socket = new Socket(address, port)) {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            outputStream.write(code.getBytes());
            int len = inputStream.read(bytes);
            String str = new String(bytes,0,len);
            return str;
        } catch (IOException e) {
            logger.error("二维码发送失败:{}", e);
            return "请求失败" + e.getMessage();
        }
    }

    @Override
    public List<EhcAppInfoDTO> getAllInfo() {
        return ehcAppInfoService.getAllEhcInfo();
    }


}