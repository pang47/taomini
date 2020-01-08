package com.taomini.core.socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 〈健康卡扫码工具〉
 *
 * @author chentao
 * @create 2020-1-8
 * @since 1.0.0
 */
public class EhcCodeSocketServer implements ServletContextListener {

    private static final String codeAddress = "127.0.0.1";
    private static final int port = 8999;
    private Socket socket = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}