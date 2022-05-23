package com.yun.xml_config.xmlPojo;

import java.io.Serializable;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/23 19:54
 */
public class Protocol implements Serializable {
    private String name;
    private String port;
    public Protocol(String name,String port){
        this.name = name;
        this.port = port;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
